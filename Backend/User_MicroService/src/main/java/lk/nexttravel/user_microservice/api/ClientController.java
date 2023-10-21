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
        System.out.println("done");
        System.out.println(reqNewClientSaveDTO.getId());
        System.out.println(reqNewClientSaveDTO.getAddress());
        System.out.println(reqNewClientSaveDTO.getName_with_initial());
        System.out.println(Arrays.toString(reqNewClientSaveDTO.getProfile_image()));
        System.out.println(reqNewClientSaveDTO.getNic_or_passport());

        return clientService.saveNewClient(reqNewClientSaveDTO);
    }

    @PostMapping(value = "/test")
    public String test(){
        return "test() invokec!";
    }
}
