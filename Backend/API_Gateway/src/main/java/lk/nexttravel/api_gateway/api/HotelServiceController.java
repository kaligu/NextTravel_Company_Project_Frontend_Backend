/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 6:26 PM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.HotelService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.util.RegaxStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 6:26 PM
 */

@RestController
@RequestMapping("/hotel-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class HotelServiceController {

    @Autowired
    HotelService hotelService;

    //user login
    @GetMapping(value = {"/hotel-admin-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileDataa(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return hotelService.UserAdminGetProfileData(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

    //Add new Guide
    @PostMapping(value = {"/create-new-hotel"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> createNewGuide(
            @RequestPart("image") String image,
            @RequestPart("name") String name,
            @RequestPart("location") String location,
            @RequestPart("locationMapLink") String locationMapLink,
            @RequestPart("referenceLink") String referenceLink,
            @RequestPart("email") String email,
            @RequestPart("categoryStar") String categoryStar,
            @RequestPart("tell1") String tell1,
            @RequestPart("tell2") String tell2,
            @RequestPart("remarks") String remarks,
            @RequestPart("isPetAllowed") String isPetAllowed,
            @RequestPart("perdayHotelFee") String perdayHotelFee,
            @RequestPart("pernightHotelFee") String pernightHotelFee,
            @RequestPart("cancellationFee") String cancellationFee,
            @RequestPart("access_username") String accessUsername,
            @RequestPart("access_jwt_token") String accessToken,
            @RequestPart("access_refresh_token") String refreshToken,

            @RequestPart("option_1_description") String option_1_description,
            @RequestPart("option_1_name") String option_1_name,
            @RequestPart("option_1_fee") String option_1_fee,

            @RequestPart("option_2_description") String option_2_description,
            @RequestPart("option_2_name") String option_2_name,
            @RequestPart("option_2_fee") String option_2_fee,

            @RequestPart("option_3_description") String option_3_description,
            @RequestPart("option_3_name") String option_3_name,
            @RequestPart("option_3_fee") String option_3_fee,

            @RequestPart("option_4_description") String option_4_description,
            @RequestPart("option_4_name") String option_4_name,
            @RequestPart("option_4_fee") String option_4_fee
    ) {
        if (true) {
            return hotelService.createNewHotel(
                    name,
                    location,
                    locationMapLink,
                    remarks,
                    referenceLink,
                    email,
                    tell1,
                    tell2,
                    categoryStar,
                    isPetAllowed,
                    perdayHotelFee,
                    pernightHotelFee,
                    cancellationFee,
                    image,
                    accessUsername,
                    accessToken,
                    refreshToken,
                    option_1_description,
                    option_1_name,
                    option_1_fee,
                    option_2_description,
                    option_2_name,
                    option_2_fee,
                    option_3_description,
                    option_3_name,
                    option_3_fee,
                    option_4_description,
                    option_4_name,
                    option_4_fee
            );
        } else {
            System.out.println("error");
            return Mono.error(new InvalidInputException("Data Invalid!"));
        }
    }
}
