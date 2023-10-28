/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:16 PM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:16 PM
 */

@RestController
@RequestMapping("/vehicle-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class VehicleServiceController {

    @Autowired
    VehicleService vehicleService;

    //user login
    @GetMapping(value = {"/vehicle-admin-get-profile-image"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileImage(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return vehicleService.UserAdminGetProfileImage(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }
}
