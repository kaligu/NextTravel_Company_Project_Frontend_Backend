/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:48 PM
*/
package lk.nexttravel.user_microservice.api;

import lk.nexttravel.user_microservice.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:48 PM
 */

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @PostMapping(value = "/save_client")
    public ResponseEntity<RespondDTO> saveClient(){

    }
}
