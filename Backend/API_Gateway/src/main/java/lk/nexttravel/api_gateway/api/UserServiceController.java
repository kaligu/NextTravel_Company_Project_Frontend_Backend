/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 7:52 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    //user login
    @GetMapping(value = "/user-login")
    public Mono<ResponseEntity<RespondDTO>> getUsernameAndProfileImage(FrontendTokenDTO frontendTokenDTO){
        InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
        if(
                internalFrontendSecurityCheckDTO.isAccesssible()
                        &&
                        internalFrontendSecurityCheckDTO.getRole().equals(Roliy)
        ){

        }else{

        }
    }
}
