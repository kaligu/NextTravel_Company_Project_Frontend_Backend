/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.DuplicateException;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.advice.util.NotfoundException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.TransactionDTO;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqNewClientSaveDTO;
import lk.nexttravel.api_gateway.entity.RefreshToken;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.TransactionCordinator;
import lk.nexttravel.api_gateway.service.UserService;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.service.mail.MailService;
import lk.nexttravel.api_gateway.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.service.security.APIGatewayJwtAccessTokenServiceFrontend;
import lk.nexttravel.api_gateway.service.security.RefreshTokenServiceFrontend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.RqRpURLs;
import org.apache.velocity.app.event.implement.EscapeXmlReference;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:52 AM
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MailService mailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    APIGatewayJwtAccessTokenServiceFrontend APIGatewayJwtAccessTokenServiceFrontend;

    @Autowired
    RefreshTokenServiceFrontend refreshTokenServiceFrontend;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    TransactionCordinator transactionCordinator;


    @Override
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
        try{
            if(userRepository.existsByName(username)){
                //Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_ALREADY_REGISTERED)
                                .repd_msg("This User is exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.OK
                );
            }else{
                //not Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_THIS_USER_NOT_REGISTERED_YET)
                                .repd_msg("This User not exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.OK
                );
            }
        }catch (Exception e){
            throw new InternalServerException("Username Check Exception Internal!");
        }
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>();
        try {
            String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
            String password = passwordEncoder.encode(userSignupDTO.getSignup_password());
            Optional<User> savedUser;
            if(!userRepository.existsByName(userSignupDTO.getSignup_name())){
                //User Save On Gateway DB -task 1
                savedUser = Optional.of(
                        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(RoleTypes.ROLE_CLIENT)
                                        .transaction_state(RespondCodes.PENDING)
                                        .build())
                );
            }else {
                throw new DuplicateException("This user already saved!");
            }


            //send data into transaction Coordinator for prepare

            transactionDTOArrayList.add(
                    TransactionDTO.builder()      //send data into User Service - task2
                            .url(RqRpURLs.User_Service_save_with_reg_user) //url
                            .httpMethod(HttpMethod.POST)  //http method
                            .data(
                                    UserReqNewClientSaveDTO.builder()
                                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                            .id(id)
                                            .address(userSignupDTO.getSignup_address())
                                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                            .profile_image(userSignupDTO.getSignup_profile_image())
                                            .name_with_initial(userSignupDTO.getSignup_name_with_initial())
                                            .build()
                            )
                            .build()
            );
            //add transactiondto arraylist for commit
            boolean isAllMicroServiceTasksCommited =false;
            isAllMicroServiceTasksCommited = transactionCordinator.preparePhaseForCreate(transactionDTOArrayList);

            if(
                    savedUser.isPresent() && isAllMicroServiceTasksCommited
            ){
                //commit user
                savedUser.get().setTransaction_state(RespondCodes.COMMITED);
                userRepository.save(savedUser.get());

                //commit
                transactionCordinator.commitPhaseForCreate(transactionDTOArrayList);

                //Access Token Create Get
                String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(userSignupDTO.getSignup_name()); //create and get JWT access token

                //UserRefreshToken Save On Gateway DB
                String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(savedUser.get()); //create get and save refresh token

                FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                        .access_username(savedUser.get().getName())
                        .access_jwt_token(newAccessToken) //create access token and assign it
                        .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                        .build();

                //send mail
                mailService.sendEmailForNewUserSignup(userSignupDTO.getSignup_email(), userSignupDTO.getSignup_name());

                //----------------------------------------------return if all are done
                return new ResponseEntity<RespondDTO> (
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                .token(frontendTokenDTO)
                                .data(savedUser.get().getRole_type())
                                .build()
                        ,
                        HttpStatus.CREATED);
            }else {
                //abrot User
                userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

                //abrot Client
                transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

                throw new InternalServerException("This User not saved!");
            }
        } catch (Exception e){

            //abrot User
            userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

            //abrot Client
            transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

            throw new InternalServerException("This User not saved!");
        }


    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> checkUsernamePasswordUserLogin(String username, String password) {
       try{
           Optional<User> user=userRepository.findUserByName(username);
           if(user.isPresent()){
               //check Username password matched
               if(
                       user.get().getName().equals(username)
                               &&
                               passwordEncoder.matches( password, user.get().getPassword())
               ){
                   //if matched
                   //Access Token Create Get
                   String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(user.get().getName()); //create and get JWT access token

                   //UserRefreshToken Save On Gateway DB
                   String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(user.get()); //create get and save refresh token

                   FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                           .access_username(user.get().getName())
                           .access_jwt_token(newAccessToken) //create access token and assign it
                           .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                           .build();

                   return Mono.just(new ResponseEntity<RespondDTO> (
                           RespondDTO.builder()
                                   .rspd_code(RespondCodes.Respond_PASSWORD_MATCHED)
                                   .token(frontendTokenDTO)
                                   .data(user.get().getRole_type())
                                   .build()
                           ,
                           HttpStatus.CREATED));
               }else{
                   return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password invalid"));
               }
           }else {
               return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password invalid"));
           }
       }catch (Exception e){
           return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username or Password invalid"));
       }
    }

    //---------------------------------- Only Testing for save admins
    //------------------------------------------------------------------------
    @Override
    public void saveNewAdminUserOnlyTesting(UserSignupDTO userSignupDTO, RoleTypes roleTypes) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>();
        Optional<User> savedUser = null;
        try {
            String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
            String password = passwordEncoder.encode(userSignupDTO.getSignup_password());

            if(!userRepository.existsByName(userSignupDTO.getSignup_name())){
                //User Save On Gateway DB -task 1
                savedUser = Optional.of(
                        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(roleTypes)
                                        .transaction_state(RespondCodes.PENDING)
                                        .build())
                );
            }else {

            }


            //send data into transaction Coordinator for prepare

            transactionDTOArrayList.add(
                    TransactionDTO.builder()      //send data into User Service - task2
                            .url(RqRpURLs.User_Service_save_with_reg_user) //url
                            .httpMethod(HttpMethod.POST)  //http method
                            .data(
                                    UserReqNewClientSaveDTO.builder()
                                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                            .id(id)
                                            .address(userSignupDTO.getSignup_address())
                                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                            .profile_image(userSignupDTO.getSignup_profile_image())
                                            .name_with_initial(userSignupDTO.getSignup_name_with_initial())
                                            .build()
                            )
                            .build()
            );
            //add transactiondto arraylist for commit
            boolean isAllMicroServiceTasksCommited =false;
            isAllMicroServiceTasksCommited = transactionCordinator.preparePhaseForCreate(transactionDTOArrayList);

            if(
                    savedUser.isPresent() && isAllMicroServiceTasksCommited
            ){
                //commit user
                savedUser.get().setTransaction_state(RespondCodes.COMMITED);
                userRepository.save(savedUser.get());

                //commit
                transactionCordinator.commitPhaseForCreate(transactionDTOArrayList);

                //Access Token Create Get
                String newAccessToken = APIGatewayJwtAccessTokenServiceFrontend.generateToken(userSignupDTO.getSignup_name()); //create and get JWT access token

                //UserRefreshToken Save On Gateway DB
                String newRefreshToken = refreshTokenServiceFrontend.createRefreshToken(savedUser.get()); //create get and save refresh token

                FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                        .access_username(savedUser.get().getName())
                        .access_jwt_token(newAccessToken) //create access token and assign it
                        .access_refresh_token(newRefreshToken)  //create refresh token and save and assign it
                        .build();

                //send mail
                mailService.sendEmailForNewUserSignup(userSignupDTO.getSignup_email(), userSignupDTO.getSignup_name());

                //----------------------------------------------return if all are done
                new ResponseEntity<RespondDTO> (
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_DATA_SAVED)
                                .token(frontendTokenDTO)
                                .data(savedUser.get().getRole_type())
                                .build()
                        ,
                        HttpStatus.CREATED);
            }else {
                //abrot User
                userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

                //abrot Client
                transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

            }
        } catch (Exception e){

            //abrot User
            userRepository.delete(userRepository.findUserByName(userSignupDTO.getSignup_name()).get());

            //abrot Client
            transactionCordinator.rollbackPhaseForCreate(transactionDTOArrayList);

        }


    }

}
