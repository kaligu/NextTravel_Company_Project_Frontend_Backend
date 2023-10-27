/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 9:18 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 9:18 AM
 */
public class UserAdminController {

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
