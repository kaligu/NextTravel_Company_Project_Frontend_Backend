/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:50 AM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:50 AM
 */
public interface UserService {

    ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username);

    ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO);

    ResponseEntity<RespondDTO> checkUsernamePasswordUserLogin(String username, String password);
}
