/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 10:22 PM
*/
package lk.nexttravel.user_microservice.api;

import lk.nexttravel.user_microservice.dto.ReqNewClientSaveDTO;
import lk.nexttravel.user_microservice.service.AdminService;
import lk.nexttravel.user_microservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 10:22 PM
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    AdminService adminService;

    //----------Save New Admin ------
    @PostMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Prepare(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Prepare(reqNewClientSaveDTO);
    }
    @PutMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Commit(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Commit(reqNewClientSaveDTO);
    }
    @DeleteMapping(value = "/save_new_admin")
    public ResponseEntity<String> SaveNewClient_Abrot(@RequestBody ReqNewClientSaveDTO reqNewClientSaveDTO){
        return adminService.SaveNewClient_Abrot(reqNewClientSaveDTO);
    }

    //get profile image - user admin
    @GetMapping(value = "/user-admin-get-profile-image")
    public ResponseEntity<String> userAdminGetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }

    //get profile image - vehicle admin
    @GetMapping(value = "/vehicle-admin-get-profile-image")
    public ResponseEntity<String> vehicleAdminGetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }

    //get profile image - hotel admin
    @GetMapping(value = "/hotel-admin-get-profile-image")
    public ResponseEntity<String> hotelAdminGetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }

    //get profile image - guide admin
    @GetMapping(value = "/guide-admin-get-profile-image")
    public ResponseEntity<String> guideAdminGetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }



}
