/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 7:52 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.service.UserService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 7:52 AM
 */

@RestController
@RequestMapping("/user-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserServiceController {

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    UserService userService;

    //user login
    @GetMapping(value = "/user-admin-get-profile-image")
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileImage(@RequestBody  FrontendTokenDTO frontendTokenDTO){
        System.out.println("invoked");
        return Mono.just(userService.UserAdminGetProfileImage(frontendTokenDTO)) ;
    }
}
