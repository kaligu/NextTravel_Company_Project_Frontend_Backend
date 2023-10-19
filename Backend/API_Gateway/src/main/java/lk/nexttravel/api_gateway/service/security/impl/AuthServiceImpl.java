/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.security.impl;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.security.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:52 AM
 */

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public ResponseEntity<RespondDTO> checkUsernameAlreadyTaken(String username) {
        return new ResponseEntity<RespondDTO>(
                RespondDTO.builder()
                        .rspd_code()
                        .rspd_message("No Username Already Taken.")
                        .data()
                        .token()
                        .data()
                        .build()
                ,
                HttpStatus.OK
        );
    }
}
