/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:26 PM
*/
package lk.nexttravel.user_microservice.api;

import lk.nexttravel.user_microservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:26 PM
 */


@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class VehicleAdminController {

    @Autowired
    AdminService adminService;

    //get profile image - user admin
    @GetMapping(value = "/vehicle-admin-get-profile-image")
    public ResponseEntity<String> GetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }
}
