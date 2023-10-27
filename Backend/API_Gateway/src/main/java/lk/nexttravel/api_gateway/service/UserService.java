/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:50 AM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:50 AM
 */
public interface UserService {

    ResponseEntity<RespondDTO> ischeckUsernameAlreadyTaken(String username);

    ResponseEntity<RespondDTO> checkUsernameAndSendOTP(String username);

    ResponseEntity<RespondDTO> verifyUsernameWithOTP(String username, String otp);

    ResponseEntity<RespondDTO> userLoginWithRecoverdPassword(String username, String otp, String password);

    ResponseEntity<RespondDTO> saveNewGuestUser(UserSignupDTO userSignupDTO);

    Mono<ResponseEntity<RespondDTO>> checkUsernamePasswordUserLogin(String username, String password);

    void saveNewAdminUserOnlyTesting(UserSignupDTO userSignupDTO, RoleTypes roleTypes);


//    Mono<ResponseEntity<RespondDTO>> getUserProfileImage(String username);
}
