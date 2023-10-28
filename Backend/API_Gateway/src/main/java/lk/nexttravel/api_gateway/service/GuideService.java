/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:47 PM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:47 PM
 */
public interface GuideService {

    //get user admin profile image
    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileImage(String access_username, String access_jwt_token, String access_refresh_token);
}
