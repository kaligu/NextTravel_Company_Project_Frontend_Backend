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
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqNewClientSaveDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.service.UserService;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.service.mail.MailService;
import lk.nexttravel.api_gateway.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.service.security.APIGatewayJwtAccessTokenServiceFrontend;
import lk.nexttravel.api_gateway.service.security.RefreshTokenServiceFrontend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.RqRpURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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


    @Override
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
//        System.out.println(username);
        try{
            //check username on DB
            if(userRepository.existsByName(username)){
//                System.out.println("duplicate");
                //Existed
                throw new DuplicateException("This username already Exists!");
            }else{
//                System.out.println("done");
                //not Existed
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Response_SUCCESS)
                                .repd_msg("This User not exists!")
                                .token(null)
                                .data(null)
                                .build()
                        ,
                        HttpStatus.OK
                );
            }
        }catch (Exception e){
//            System.out.println("exception");
            throw new InternalServerException("Username Check Exception Internal!");
        }
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO) {
        //Generate ID using previous DB User ID
        String id = "U00"+sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME);
        //encode string of password
        String password = passwordEncoder.encode(userSignupDTO.getSignup_password());

        //--------------------------------------------Send data into User MicroService
        try {
            // Create a RestTemplate instance
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Create a request entity with the request body and headers
            HttpEntity<UserReqNewClientSaveDTO> requestEntity = new HttpEntity<>(
                    UserReqNewClientSaveDTO.builder()
                            .token( apiGatewayJwtAccessTokenServiceBackend.generateToken() ) //create a session token to connect with microservice check this request is valid request
                            .id(id)
                            .address(userSignupDTO.getSignup_address())
                            .nic_or_passport(userSignupDTO.getSignup_nic_or_passport())
                            .profile_image(userSignupDTO.getSignup_profile_image())
                            .name_with_initial(userSignupDTO.getSignup_name_with_initial())
                            .build(), headers);

            ResponseEntity<RespondDTO> responseEntity = restTemplate.exchange(
                    RqRpURLs.User_Service_save_with_reg_user,
                    HttpMethod.POST,
                    requestEntity,
                    RespondDTO.class
            );
            //##---------------First Response authentication-----------------------------
            String token = (String) responseEntity.getBody().getToken();
            if(apiGatewayJwtAccessTokenServiceBackend.isTokenValid(token)){ //check recieved token first
                //check if not saved throw exception
                if(!responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
                    throw new InternalServerException("This User not saved! User Micro Serive Error!");
                }
            }else {
                throw new InternalServerException("Not valid token received! User Micro Serive Error!");
            }

        }catch (Exception e){
            throw new InternalServerException("This User not saved! User Micro Serive Error!");
        }

        ///--------------------------------------save data AuthUser Repo
        userRepository.save(
                                User.builder()
                                        .id(id)
                                        .name(userSignupDTO.getSignup_name())
                                        .email(userSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(RoleTypes.ROLE_CLIENT)
                                        .build());
        //Check saved and Generate tokens and save and return
        Optional<User> authUser = userRepository.findAuthUserByName(userSignupDTO.getSignup_name());

        //----------------------------Create Jwtaccess token and create,save data Refresh token
        if(authUser.isPresent()){   //first check already saved AuthUser
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                    .access_username(authUser.get().getName())
                    .access_jwt_token(APIGatewayJwtAccessTokenServiceFrontend.generateToken(authUser.get().getName())) //create access token and assign it
                    .access_refresh_token(refreshTokenServiceFrontend.createRefreshToken(authUser.get()))  //create refresh token and save and assign it
                    .build();

            //send confirmation mail to register user
            //send mail
            mailService.sendEmailForNewUserSignup(userSignupDTO.getSignup_email(), userSignupDTO.getSignup_name());

            //----------------------------------------------return if all are done
            return new ResponseEntity<RespondDTO> (
                    RespondDTO.builder()
                            .rspd_code(RespondCodes.Response_DATA_SAVED)
                            .token(frontendTokenDTO)
                            .data(null)
                            .build()
                    ,
                    HttpStatus.CREATED);
        }else{
            throw new InternalServerException("This User not saved!");
        }
    }

}
