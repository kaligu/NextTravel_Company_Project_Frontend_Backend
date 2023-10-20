/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.security.impl;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.AuthSignupDTO;
import lk.nexttravel.api_gateway.service.security.AuthService;
import lk.nexttravel.api_gateway.util.RespondCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:52 AM
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
        return new ResponseEntity<RespondDTO>(
                RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_SUCCESS)
                        .rspd_message("Username Already Taken.")
                        .token(null)
                        .data(false)
                        .build()
                ,
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<RespondDTO> saveNewGuestUser(AuthSignupDTO authSignupDTO) {
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
                        .rspd_message("New User Registered Successfully!")
                        .token(null)
                        .data(null)
                        .build()
                ,
                HttpStatus.CREATED);
    }
}
