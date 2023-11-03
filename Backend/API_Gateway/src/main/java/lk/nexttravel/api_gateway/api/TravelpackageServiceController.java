/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 9:44 PM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.TravelpackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 9:44 PM
 */

@RestController
@RequestMapping("/travelpackage-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class TravelpackageServiceController {

    @Autowired
    TravelpackageService travelpackageService;

    //user login
    @GetMapping(value = {"/travelpackage-admin-get-profile-data"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileData(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return travelpackageService.UserAdminGetProfileData(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

    //Add new Guide
    @PostMapping(value = {"/create-new-travelpack"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> createNewTravelPack(
            @RequestPart("travelpackage_name") String travelpackage_name,
            @RequestPart("travelpackage_travel_areas") String travelpackage_travel_areas,
            @RequestPart("travelpackage_category") String travelpackage_category,
            @RequestPart("travelpackage_service_charge") String travelpackage_service_charge,
            @RequestPart("travelpackage_travelling_length_km") String travelpackage_travelling_length_km,
            @RequestPart("travelpackage_travelling_days") String travelpackage_travelling_days,
            @RequestPart("travelpackage_hotel_booking_type") String travelpackage_hotel_booking_type,
            @RequestPart("travelpackage_hotel_booking_nigths") String travelpackage_hotel_booking_nigths,
            @RequestPart("travelpackage_hotel_booking_days") String travelpackage_hotel_booking_days,
            @RequestPart("vedio_name") String vedio_name,
            @RequestPart("vedio_content") String vedio_content,
            @RequestPart("vedio_link") String vedio_link,
            @RequestPart("insuarance_name") String insuarance_name,
            @RequestPart("insuarance_description") String insuarance_description,
            @RequestPart("insuarance_policies") String insuarance_policies,
            @RequestPart("insuarance_covergae_value") String insuarance_covergae_value,
            @RequestPart("promotion_name") String promotion_name,
            @RequestPart("promotion_content") String promotion_content,
            @RequestPart("promotion_rate") String promotion_rate,
            @RequestPart("promotion_start_date") String promotion_start_date,
            @RequestPart("promotion_end_date") String promotion_end_date,
            @RequestPart("access_username") String access_username,
            @RequestPart("access_jwt_token") String access_jwt_token,
            @RequestPart("access_refresh_token") String access_refresh_token
    ){

        if (true) {
            return travelpackageService.createNewTravelPack(
                    travelpackage_name,
                    travelpackage_travel_areas,
                    travelpackage_category,
                    travelpackage_service_charge,
                    travelpackage_travelling_length_km,
                    travelpackage_travelling_days,
                    travelpackage_hotel_booking_type,
                    travelpackage_hotel_booking_nigths,
                    travelpackage_hotel_booking_days,
                    vedio_name,
                    vedio_content,
                    vedio_link,
                    insuarance_name,
                    insuarance_description,
                    insuarance_policies,
                    insuarance_covergae_value,
                    promotion_name,
                    promotion_content,
                    promotion_rate,
                    promotion_start_date,
                    promotion_end_date,
                    access_username,
                    access_jwt_token,
                    access_refresh_token
            );
        } else {
            System.out.println("error");
            return Mono.error(new InvalidInputException("Data Invalid!"));
        }
    }
}
