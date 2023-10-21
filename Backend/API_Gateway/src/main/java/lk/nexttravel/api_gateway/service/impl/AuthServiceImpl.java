/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.AuthUserRepository;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.AuthSignupDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.user.ReqNewClientSaveDTO;
import lk.nexttravel.api_gateway.entity.AuthUser;
import lk.nexttravel.api_gateway.service.AuthService;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenService;
import lk.nexttravel.api_gateway.service.security.util.RefreshTokenService;
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
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthUserRepository authUserRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    APIGatewayJwtAccessTokenService APIGatewayJwtAccessTokenService;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
        return new ResponseEntity<RespondDTO>(
                RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_SUCCESS)
                        .token(null)
                        .data(false)
                        .build()
                ,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewGuestUser(AuthSignupDTO authSignupDTO) {
        //Generate ID using previous DB User ID
        String id = "U00"+sequenceGeneratorService.generateSequence(AuthUser.SEQUENCE_NAME);
        //encode string of password
        String password = passwordEncoder.encode(authSignupDTO.getSignup_password());

        //--------------------------------------------Send data into User MicroService
        try {
            // Create a RestTemplate instance
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Create a request entity with the request body and headers
            HttpEntity<ReqNewClientSaveDTO> requestEntity = new HttpEntity<>(
                    ReqNewClientSaveDTO.builder()
                            .id(id)
                            .address(authSignupDTO.getSignup_address())
                            .nic_or_passport(authSignupDTO.getSignup_nic_or_passport())
                            .profile_image(authSignupDTO.getSignup_profile_image())
                            .name_with_initial(authSignupDTO.getSignup_name_with_initial())
                            .build(), headers);

            ResponseEntity<RespondDTO> responseEntity = restTemplate.exchange(
                    RqRpURLs.User_Service_save_with_reg_user,
                    HttpMethod.POST,
                    requestEntity,
                    RespondDTO.class
            );
            //check if not saved
            if(!responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
                throw new InternalServerException("This User not saved! User Micro Serive Error!");
            }
        }catch (Exception e){
            throw new InternalServerException("This User not saved! User Micro Serive Error!");
        }

        ///--------------------------------------save data AuthUser Repo
        authUserRepository.save(
                                AuthUser.builder()
                                        .id(id)
                                        .name(authSignupDTO.getSignup_name())
                                        .email(authSignupDTO.getSignup_email())
                                        .password(password)
                                        .role_type(RoleTypes.ROLE_CLIENT)
                                        .build());
        //Check saved and Generate tokens and save and return
        Optional<AuthUser> authUser = authUserRepository.findAuthUserByName(authSignupDTO.getSignup_name());

        //----------------------------Create Jwtaccess token and create,save data Refresh token
        if(authUser.isPresent()){   //first check already saved AuthUser
            FrontendTokenDTO frontendTokenDTO = FrontendTokenDTO.builder()
                    .access_username(authUser.get().getName())
                    .access_jwt_token(APIGatewayJwtAccessTokenService.generateToken(authUser.get().getName())) //create access token and assign it
                    .access_refresh_token(refreshTokenService.createRefreshToken(authUser.get()))  //create refresh token and save and assign it
                    .build();

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
