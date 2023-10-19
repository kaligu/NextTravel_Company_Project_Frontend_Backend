/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:39 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:39 AM
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:63342/")
public class AuthController {

    //checkUsername
    @GetMapping(value = "/check-username")
    public Mono<ResponseEntity<RespondDTO>> checkUsername(@RequestParam("username") String username){
        ResponseEntity<RespondDTO> respondDTO = userService.loginUser(reqLoginDTO);
        return Mono.just(respondDTO);
    }
}
