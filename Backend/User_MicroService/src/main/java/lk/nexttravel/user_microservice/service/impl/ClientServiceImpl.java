/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:43 PM
*/
package lk.nexttravel.user_microservice.service.impl;

import lk.nexttravel.user_microservice.advice.util.NotfoundException;
import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import lk.nexttravel.user_microservice.entity.Client;
import lk.nexttravel.user_microservice.persistence.ClientRepository;
import lk.nexttravel.user_microservice.service.ClientService;
import lk.nexttravel.user_microservice.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:43 PM
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public ResponseEntity<RespondDTO> saveNewClient(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        System.out.println(reqNewClientSaveDTO.getId());
        System.out.println(reqNewClientSaveDTO.getAddress());
        System.out.println(reqNewClientSaveDTO.getName_with_initial());
        System.out.println(Arrays.toString(reqNewClientSaveDTO.getProfile_image()));
        System.out.println(reqNewClientSaveDTO.getNic_or_passport());

        //save into database
        clientRepository.save(
                Client.builder()
                        .id(reqNewClientSaveDTO.getId())
                        .name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                        .address(reqNewClientSaveDTO.getAddress())
                        .profile_image(reqNewClientSaveDTO.getProfile_image())
                        .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                        .build()
        );

        //check done saved
        Optional<Client> client = clientRepository.findClientById(reqNewClientSaveDTO.getId());
        if(client.isPresent()){
            System.out.println("yes"+RespondCodes.Response_DATA_SAVED);
            return new ResponseEntity<RespondDTO>(
                    (RespondDTO.builder()
                            .rspd_code(RespondCodes.Response_DATA_SAVED)
                            .rspd_code("Client Saved! - Backend User Micro Service")
                            .token(null)
                            .data(client)
                            .build()
                    ),
                    HttpStatus.ACCEPTED
            );
        }else{
            System.out.println("no");
            throw new NotfoundException("Not saved this Client! - Backend User Micro Service");
        }
    }
}
