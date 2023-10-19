/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:50 AM
*/
package lk.nexttravel.api_gateway.service.security;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:50 AM
 */
public interface AuthService {
    ResponseEntity<RespondDTO> checkUsernameAlreadyTaken(String username);
}
