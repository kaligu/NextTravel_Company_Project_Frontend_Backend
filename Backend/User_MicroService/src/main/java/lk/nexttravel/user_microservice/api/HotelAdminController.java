/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 4:04 PM
*/
package lk.nexttravel.user_microservice.api;

import lk.nexttravel.user_microservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 4:04 PM
 */

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class HotelAdminController {

    @Autowired
    AdminService adminService;

    //get profile image - user admin
    @GetMapping(value = "/hotel-admin-get-profile-image")
    public ResponseEntity<String> GetProfileImage(
            @RequestParam String id ,
            @RequestParam String token
    ){
        return adminService.userAdminGetProfileImage(id,token);
    }
}
