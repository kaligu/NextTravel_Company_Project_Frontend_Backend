/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 11:04 PM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 11:04 PM
 */
public interface UserService {
    //get user admin profile image
    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileImage(
            String access_username,
            String access_jwt_token,
            String access_refresh_token);

    //get all admins
    Mono<ResponseEntity<RespondDTO>> getAllAdminsBySearch(
            String search_keyword ,
            String access_username,
            String access_jwt_token,
            String access_refresh_token);

    //request to logout
    Mono<ResponseEntity<RespondDTO>> requestToLogout(
            String access_username,
            String access_jwt_token,
            String access_refresh_token);
}
