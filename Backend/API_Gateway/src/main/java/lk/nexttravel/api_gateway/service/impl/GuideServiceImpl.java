/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:49 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.DuplicateException;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.advice.util.NotfoundException;
import lk.nexttravel.api_gateway.advice.util.UnauthorizeException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.TransactionDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.dto.user.ReqProfileDataAdminsDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqNewClientSaveDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqProfileDataDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.GuideService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.RqRpURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:49 PM
 */

@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token) {
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {
                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                User user = userRepository.findUserByName(frontendTokenDTO.getAccess_username()).get();

                ResponseEntity<ReqProfileDataAdminsDTO> reqProfileDataAdminsDTOResponseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/admin/user-admin-get-profile-data?id=" + user.getId() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        ReqProfileDataAdminsDTO.class
                );
                //send to front
                return Mono.just(new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_SUCCESS)
                                .data(
                                        UserReqProfileDataDTO.builder()
                                                .name(user.getName())
                                                .name_with_initial(reqProfileDataAdminsDTOResponseEntity.getBody().getName_with_initial())
                                                .address(reqProfileDataAdminsDTOResponseEntity.getBody().getAddress())
                                                .email(user.getEmail())
                                                .profile_image(reqProfileDataAdminsDTOResponseEntity.getBody().getProfile_image())
                                                .nic_or_passport(reqProfileDataAdminsDTOResponseEntity.getBody().getNic_or_passport())
                                                .build()
                                )
                                .token(
                                        FrontendTokenDTO.builder()
                                                .access_username(internalFrontendSecurityCheckDTO.getUsername())
                                                .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                                                .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                                                .build()
                                )
                                .build()
                        ,
                        HttpStatus.OK
                ));
            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }
    }

    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminUpdateProfileData(String username, String address, String email, String nic, String password, String nameinitial, String profileImage_base64String, String access_username, String access_jwt_token, String access_refresh_token) {
        ArrayList<TransactionDTO> transactionDTOArrayList = new ArrayList<>(); // for transactions
        try{
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder().access_jwt_token(access_jwt_token).access_username(access_username).access_refresh_token(access_refresh_token).build();
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_GUIDE)
            ) {

                String encodedpassword = passwordEncoder.encode(password);

                Optional<User> savedUser;
                if(userRepository.existsByName(username)){
                    //User Save On Gateway DB -task 1
                    savedUser = Optional.of(
                            userRepository.save(
                                    User.builder()
                                            .name(username)
                                            .email(email)
                                            .password(encodedpassword)
                                            .transaction_state(RespondCodes.PENDING)
                                            .build())
                    );
                }else {
                    throw new Exception("Not found");
                }

                //send data into transaction Coordinator for prepare

                transactionDTOArrayList.add(
                        TransactionDTO.builder()      //send data into User Service - task2
                                .url(RqRpURLs.User_Service_Guide_Update) //url
                                .httpMethod(HttpMethod.POST)  //http method
                                .data(
                                        UserReqNewClientSaveDTO.builder()
                                                .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                                .id(id)
                                                .address(userSignupDTO.getSignup_address())
                                                .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                                .profile_image("encodedImage")
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

            }else {
                return Mono.error(new UnauthorizeException("Unauthorized request"));
            }
        }catch (Exception e){
            return Mono.error(new InternalServerException("Internal Server error!"+e));
        }




        try {
            String encodedpassword = passwordEncoder.encode(password);

            Optional<User> savedUser;
            if(userRepository.existsByName(u)){
                //User Save On Gateway DB -task 1
                savedUser = Optional.of(
                        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(encodedpassword)
                                        .role_type(RoleTypes.ROLE_CLIENT)
                                        .transaction_state(RespondCodes.PENDING)
                                        .build())
                );
            }else {
                throw new Exception("Not found");
            }


            //send data into transaction Coordinator for prepare

            transactionDTOArrayList.add(
                    TransactionDTO.builder()      //send data into User Service - task2
                            .url(RqRpURLs.User_Service_New_Client_Save_Signup) //url
                            .httpMethod(HttpMethod.POST)  //http method
                            .data(
                                    UserReqNewClientSaveDTO.builder()
                                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                                            .id(id)
                                            .address(userSignupDTO.getSignup_address())
                                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                                            .profile_image("encodedImage")
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
}
