/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:48 PM
*/
package lk.nexttravel.user_microservice.api;

import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import lk.nexttravel.user_microservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:48 PM
 */

@RestController
@RequestMapping("/client")
@CrossOrigin("*")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping(value = "/save_new_client")
    public ResponseEntity<RespondDTO> saveNewClient(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        System.out.println("inb");
        return clientService.saveNewClient(reqNewClientSaveDTO);
    }

}
