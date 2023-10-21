/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.AuthUserRepository;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.AuthSignupDTO;
import lk.nexttravel.api_gateway.entity.AuthUser;
import lk.nexttravel.api_gateway.service.AuthService;
import lk.nexttravel.api_gateway.service.SequenceGeneratorService;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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

//        WebClient.create()
//                .post()
//                .uri(RqRpURLs.User_Service_save_with_reg_user)
//                .bodyValue(
//                        ReqClientRegUserDTO.builder()
//                                .id(id)
//                                .address(reqSignupDTO.getSignup_address())
//                                .nic_or_passport(reqSignupDTO.getSignup_nic_or_passport())
//                                .profile_picture(profileStringData)
//                                .build()
//                )
//                .retrieve()
//                .bodyToMono(String.class)
//                .subscribe(response -> {
//                    // Handle the response here
//                });



        System.out.println("AuthSignupDTO{" +
                "signup_name='" + authSignupDTO.getSignup_name() + '\'' +
                ", signup_name with initial='" + authSignupDTO.getSignup_name_with_initial() + '\'' +
                ", signup_email='" + authSignupDTO.getSignup_email() + '\'' +
                ", signup_password='" + authSignupDTO.getSignup_password() + '\'' +
                ", signup_nic_or_passport='" + authSignupDTO.getSignup_nic_or_passport() + '\'' +
                ", signup_address='" + authSignupDTO.getSignup_address() + '\'' +
                ", signup_profile_image=" + Arrays.toString(authSignupDTO.getSignup_profile_image()) +
                '}');


        return new ResponseEntity<RespondDTO> (
                RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_DATA_SAVED)
                        .token(null)
                        .data(null)
                        .build()
                ,
                HttpStatus.CREATED);
    }

    @Override
    public Optional<RoleTypes> getRoleByUsername(String username) {
        Optional<AuthUser> user = authUserRepository.findAuthUserByName(username);
        if (user.isPresent()) {
            return Optional.of(user.get().getRole_type());
        } else {
            return Optional.empty();
        }
    }
}
