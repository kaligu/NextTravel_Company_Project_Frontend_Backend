/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:52 AM
*/
package lk.nexttravel.api_gateway.service.security.impl;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.security.AuthService;
import lk.nexttravel.api_gateway.util.RespondCodes;
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
    public ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username) {
        System.out.println(username);
        return new ResponseEntity<RespondDTO>(
                RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_SUCCESS)
                        .rspd_message("Username Already Taken.")
                        .token(null)
                        .data(true)
                        .build()
                ,
                HttpStatus.OK
        );
    }
}
