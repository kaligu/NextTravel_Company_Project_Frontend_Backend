/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:43 PM
*/
package lk.nexttravel.user_microservice.service.impl;

import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import lk.nexttravel.user_microservice.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:43 PM
 */

@Service
public class ClientServiceImpl implements ClientService {
    @Override
    public ResponseEntity<RespondDTO> saveNewClient(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        return null;
    }
}
