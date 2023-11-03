/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:16 PM
*/
package lk.nexttravel.api_gateway.api;

import lk.nexttravel.api_gateway.advice.util.InvalidInputException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:16 PM
 */

@RestController
@RequestMapping("/vehicle-service")
@CrossOrigin(origins = "http://localhost:63342/")
public class VehicleServiceController {

    @Autowired
    VehicleService vehicleService;

    //user login
    @GetMapping(value = {"/vehicle-admin-get-profile-image"}, consumes = {"application/json"})
    public Mono<ResponseEntity<RespondDTO>> userAdminGetProfileImage(
            @RequestParam("access_username") @NonNull String access_username
            ,
            @RequestParam("access_jwt_token") @NonNull String access_jwt_token
            ,
            @RequestParam("access_refresh_token") @NonNull String access_refresh_token
    ){
        return vehicleService.UserAdminGetProfileImage(
                access_username
                ,
                access_jwt_token
                ,
                access_refresh_token
        );
    }

    //Add new Vehicle
    @PostMapping(value = {"/create-new-vehicle"}, consumes = {"multipart/form-data"})
    public Mono<ResponseEntity<RespondDTO>> createNewGuide(
            @RequestPart("vehicle_type") String vehicle_type,
            @RequestPart("vehicle_fuel_type") String vehicle_fuel_type,
            @RequestPart("vehicle_hybrid_or_non_hybrid") String vehicle_hybrid_or_non_hybrid,
            @RequestPart("vehicle_seat_capacity") String vehicle_seat_capacity,
            @RequestPart("vehicle_transmission_type") String vehicle_transmission_type,
            @RequestPart("vehicle_fuel_usage") String vehicle_fuel_usage,
            @RequestPart("vehicle_perday_vehicle_fee") String vehicle_perday_vehicle_fee,
            @RequestPart("vehicle_category") String vehicle_category,

            @RequestPart("vehicle_image_sideview") String vehicle_image_sideview,
            @RequestPart("vehicle_image_frontview") String vehicle_image_frontview,
            @RequestPart("vehicle_image_rearview") String vehicle_image_rearview,
            @RequestPart("vehicle_image_front_interior_view") String vehicle_image_front_interior_view,
            @RequestPart("vehicle_image_rear_interior_view") String vehicle_image_rear_interior_view,

            @RequestPart("vehicle_driver_name") String vehicle_driver_name,
            @RequestPart("vehicle_driver_tell") String vehicle_driver_tell,
            @RequestPart("vehicle_driver_license_rear_view") String vehicle_driver_license_rear_view,
            @RequestPart("vehicle_driver_license_front_view") String vehicle_driver_license_front_view,
            @RequestPart("vehicle_driver_remarks") String vehicle_driver_remarks,

            @RequestPart("access_username") String accessUsername,
            @RequestPart("access_jwt_token") String accessToken,
            @RequestPart("access_refresh_token") String refreshToken
    ) {
        if (true) {
            return vehicleService.createNewVehcile(
                    vehicle_type,
                    vehicle_fuel_type,
                    vehicle_hybrid_or_non_hybrid,
                    vehicle_seat_capacity,
                    vehicle_transmission_type,
                    vehicle_fuel_usage,
                    vehicle_perday_vehicle_fee,
                    vehicle_category,

                    vehicle_image_sideview,
                    vehicle_image_frontview,
                    vehicle_image_rearview,
                    vehicle_image_front_interior_view,
                    vehicle_image_rear_interior_view,

                    vehicle_driver_name,
                    vehicle_driver_tell,
                    vehicle_driver_license_rear_view,
                    vehicle_driver_license_front_view,
                    vehicle_driver_remarks,

                    accessUsername,
                    accessToken,
                    refreshToken
            );
        } else {
            System.out.println("error");
            return Mono.error(new InvalidInputException("Data Invalid!"));
        }
    }
}
