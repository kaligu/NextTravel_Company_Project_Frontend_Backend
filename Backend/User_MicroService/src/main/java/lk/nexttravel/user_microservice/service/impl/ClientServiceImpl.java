/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:43 PM
*/
package lk.nexttravel.user_microservice.service.impl;

import lk.nexttravel.user_microservice.advice.util.InternalServerException;
import lk.nexttravel.user_microservice.advice.util.NotfoundException;
import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.ReqProfileDataAdminsDTO;
import lk.nexttravel.user_microservice.dto.RequestServicesDTO;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import lk.nexttravel.user_microservice.entity.Admin;
import lk.nexttravel.user_microservice.entity.Client;
import lk.nexttravel.user_microservice.persistence.ClientRepository;
import lk.nexttravel.user_microservice.service.ClientService;
import lk.nexttravel.user_microservice.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.user_microservice.util.RespondCodes;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:43 PM
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<String> SaveNewClient_Prepare(ReqNewClientSaveDTO reqNewClientSaveDTO) {

        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token
                //save into database
                Client client = clientRepository.save(
                        Client.builder()
                                .id(reqNewClientSaveDTO.getId())
                                .name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                .address(reqNewClientSaveDTO.getAddress())
                                .profile_image(reqNewClientSaveDTO.getProfile_image())
                                .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                .transaction_state(RespondCodes.PENDING)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> SaveNewClient_Commit(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token
                //save into database
                clientRepository.save(
                        Client.builder()
                                .id(reqNewClientSaveDTO.getId())
                                .name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                .address(reqNewClientSaveDTO.getAddress())
                                .profile_image(reqNewClientSaveDTO.getProfile_image())
                                .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                .transaction_state(RespondCodes.COMMITED)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewClientSaveDTO.getToken())) {  //check gateway token
                //delete
                clientRepository.delete(
                        Client.builder()
                                .id(reqNewClientSaveDTO.getId())
                                .name_with_initial(reqNewClientSaveDTO.getName_with_initial())
                                .address(reqNewClientSaveDTO.getAddress())
                                .profile_image(reqNewClientSaveDTO.getProfile_image())
                                .nic_or_passport(reqNewClientSaveDTO.getNic_or_passport())
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_DELETED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public  ResponseEntity<ReqProfileDataAdminsDTO> getClientData(String id, String token) {
        try {
            System.out.println("done");
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(token)) {  //check gateway token
                //get image string
                Optional<Client> admin = clientRepository.findById(id);
                if(admin.isPresent()){

                    return  new ResponseEntity<> (
                            ReqProfileDataAdminsDTO.builder()
                                    .name_with_initial(admin.get().getName_with_initial())
                                    .nic_or_passport(admin.get().getNic_or_passport())
                                    .address(admin.get().getAddress())
                                    .profile_image(admin.get().getProfile_image())
                                    .build()
                            , HttpStatus.OK);

                }else{
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }

            }else{
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
