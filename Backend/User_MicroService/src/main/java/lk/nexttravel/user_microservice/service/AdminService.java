/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:44 PM
*/
package lk.nexttravel.user_microservice.service;

import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.dto.ReqProfileDataAdminsDTO;
import lk.nexttravel.user_microservice.entity.Admin;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:44 PM
 */
public interface AdminService {
    ResponseEntity<String> SaveNewClient_Prepare(ReqNewClientSaveDTO reqNewClientSaveDTO);

    ResponseEntity<String> SaveNewClient_Commit(ReqNewClientSaveDTO reqNewClientSaveDTO);

    ResponseEntity<String> SaveNewClient_Abrot(ReqNewClientSaveDTO reqNewClientSaveDTO);

    //get user admin profile image
    ResponseEntity<ReqProfileDataAdminsDTO> userAdminGetProfileData(String id, String token);

    //get admin by id
    ResponseEntity<Admin> getAllAdminsSataNySearch(String id , String token);
}
