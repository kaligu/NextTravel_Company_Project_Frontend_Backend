/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 9:46 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.TravelpackageService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 9:46 PM
 */

@Service
public class TravelpackageServiceImpl implements TravelpackageService {
    @Override
    public Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileImage(String access_username, String access_jwt_token, String access_refresh_token) {
        return null;
    }
}
