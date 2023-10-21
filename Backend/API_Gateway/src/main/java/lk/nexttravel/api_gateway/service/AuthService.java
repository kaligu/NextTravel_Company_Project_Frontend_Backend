/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:50 AM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.AuthSignupDTO;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:50 AM
 */
public interface AuthService {

    ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username);

    ResponseEntity<RespondDTO> saveNewGuestUser(AuthSignupDTO authSignupDTO);

}
