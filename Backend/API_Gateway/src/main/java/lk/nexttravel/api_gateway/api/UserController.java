/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 4:39 AM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.UserSignupDTO;
import lk.nexttravel.api_gateway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:39 AM
 */

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:63342/")
public class UserController {

    @Autowired
    UserService userService;

    //checkUsername
    @GetMapping(value = "/ischeck-username")
    public Mono<ResponseEntity<RespondDTO>> checkUsername(@RequestParam("username") @NonNull String username) {
        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")) {   //check Username Regax
            return Mono.just(
                    userService.ischeckUsernameAlreadyTaken(username)
            );
        } else {
            throw new InvalidInputException("Username is invalid!");
        }

    }

    //save user
    @PostMapping(value = "/signup-guestuser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<RespondDTO>> saveNewGuestUser(@RequestPart("signup_name") @NonNull String name,
                                                             @RequestPart("signup_name_with_initial") @NonNull String nameWithInitial,
                                                             @RequestPart("signup_email") @NonNull String email,
                                                             @RequestPart("signup_password") @NonNull String password,
                                                             @RequestPart("signup_nic_or_passport") @NonNull String nicOrPassport,
                                                             @RequestPart("signup_address") @NonNull String addres,
                                                             @RequestPart("signup_profile_image") byte[] image) {

        if (name.matches("^[a-zA-Z0-9_.-]{5,30}$")) {                                         //check Username Regax
            if (nameWithInitial.matches("([A-Z])\\w+\\s([A-Z])\\w*\\s*([A-Z])*(?=,*)")) {     //check Name with Initial Regax
                if (email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {     //check Email Regax
                    if (password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {         //check Password Regax
                        if (nicOrPassport.matches("^[a-zA-Z0-9_-]+\\S{8,11}$")) {             //check Nic or Passport Regax
                            if (addres.matches("^\\S+\\s*[a-zA-Z0-9,.-]+\\S{0,48}$")) {       //check Address Regax
                                if (image != null) {

                                    return Mono.just(
                                            userService.saveNewGuestUser(
                                                    UserSignupDTO.builder()
                                                            .signup_name(name)
                                                            .signup_name_with_initial(nameWithInitial)
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
                throw new InvalidInputException("Name with initialis invalid!");
            }
        } else {
            throw new InvalidInputException("Username is invalid!");
        }
    }

//    //user login
//    @GetMapping(value = "/login-user")
//    public Mono<ResponseEntity<RespondDTO>> checkUsername(@RequestParam("username") @NonNull String username) {
//        if (username.matches("^[a-zA-Z0-9_.-]{5,30}$")) {   //check Username Regax
//            return Mono.just(
//                    userService.ischeckUsernameAlreadyTaken(username)
//            );
//        } else {
//            throw new InvalidInputException("Username is invalid!");
//        }
//
//    }
}