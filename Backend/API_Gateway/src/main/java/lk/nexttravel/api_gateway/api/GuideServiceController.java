/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:46 PM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.GuideService;
import lk.nexttravel.api_gateway.util.RegaxStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:46 PM
 */

@RestController
@RequestMapping("/guide-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class GuideServiceController {

    @Autowired
    GuideService guideService;

    //user login
    @GetMapping(value = {"/guide-admin-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileImage(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return guideService.UserAdminGetProfileData(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

    //update Profile Data - guide admin
    @PostMapping(value = {"/guide-admin-update-profile-data"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> userAdminUpdateProfileData(
            @RequestPart("username") String username,
            @RequestPart("address") String address,
            @RequestPart("email") String email,
            @RequestPart("nic") String nic,
            @RequestPart("password") String password,
            @RequestPart("nameinitial") String nameinitial,
            @RequestPart("profileImage_Base64String") String profileImage_Base64String,
            @RequestPart("access_username") String access_username,
            @RequestPart("access_jwt_token") String access_jwt_token,
            @RequestPart("access_refresh_token") String access_refresh_token
    ){
        if(
                username.matches(RegaxStrings.NameRegax) &&
                        address.matches(RegaxStrings.AddressRegax) &&
                        email.matches(RegaxStrings.EmailRegax) &&
                        nic.matches(RegaxStrings.NICRegax) &&
                        password.matches(RegaxStrings.PasswordRegax) &&
                        nameinitial.matches(RegaxStrings.NameWithInitialRegax)


        ){

            return guideService.UserAdminUpdateProfileData(
                    username,
                    address,
                    email,
                    nic,
                    password,
                    nameinitial,
                    profileImage_Base64String,
                    access_username,
                    access_jwt_token,
                    access_refresh_token
            );
        }else {
            System.out.println("error");
            return Mono.error( new InvalidInputException("Data Invalid!") );
        }
    }
}
