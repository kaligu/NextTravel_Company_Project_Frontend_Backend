/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:39 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.AuthSignupDTO;
import lk.nexttravel.api_gateway.service.security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:39 AM
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:63342/")
public class AuthController {

    @Autowired
    AuthService authService;

    //checkUsername
    @GetMapping(value = "/ischeck-username")
    public Mono<ResponseEntity<RespondDTO>> checkUsername(@RequestParam("username") @NonNull String username){
        if(username.length() > 3){

            return Mono.just(
                    authService.ischeckUsernameAlreadyTaken(username)
            );

        }else{
            throw new InvalidInputException("Username is invalid!");
        }

    }

    //save user
    @PostMapping(value = "/signup-guestuser",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<RespondDTO>> saveNewGuestUser(@RequestPart("signup_name") @NonNull String name,
                                                            @RequestPart("signup_email" ) @NonNull String email,
                                                            @RequestPart("signup_password") @NonNull String password,
                                                            @RequestPart("signup_nic_or_passport") @NonNull String nicOrPassport,
                                                            @RequestPart("signup_address") @NonNull String addres,
                                                            @RequestPart("signup_profile_image")  byte[] image )
    {

        if (name.length() > 3) {
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                if (password.length() > 3) {
                    if (nicOrPassport.matches("^(?:[0-9]{9}[x|X|v|V]|[0-9]{12})$")) {
                        if (addres.matches("^[a-zA-Z0-9\\s.,]+$")) {
                            if(image != null) {

                                return Mono.just(
                                        authService.saveNewGuestUser(
                                                AuthSignupDTO.builder()
                                                        .signup_name(name)
                                                        .signup_address(addres)
                                                        .signup_email(email)
                                                        .signup_nic_or_passport(nicOrPassport)
                                                        .signup_password(password)
                                                        .signup_profile_image(image)
                                                        .build()
                                        )
                                );

                            } else {
                                throw new InvalidInputException("Image data is invalid!");
                            }
                        } else {
                            throw new InvalidInputException("Address data is invalid!");
                        }
                    } else {
                        throw new InvalidInputException("NIC or Passport data is invalid!");
                    }
                } else {
                    throw new InvalidInputException("Password data is invalid!");
                }
            } else {
                throw new InvalidInputException("Email is invalid!");
            }
        } else {
            throw new InvalidInputException("Username is invalid!");
        }
    }
}
