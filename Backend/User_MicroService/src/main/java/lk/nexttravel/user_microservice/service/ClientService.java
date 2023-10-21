/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:43 PM
*/
package lk.nexttravel.user_microservice.service;

import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:43 PM
 */
public interface ClientService {
    ResponseEntity<RespondDTO> saveNewClient(ReqNewClientSaveDTO reqNewClientSaveDTO);
}
