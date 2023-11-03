/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 7:17 PM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 7:17 PM
 */
public interface VehicleService {

    Mono<ResponseEntity<RespondDTO>> createNewVehcile(String vehicle_type, String vehicle_fuel_type, String vehicle_hybrid_or_non_hybrid, String vehicle_seat_capacity, String vehicle_transmission_type, String vehicle_fuel_usage, String vehicle_perday_vehicle_fee, String vehicle_category, String vehicle_image_sideview, String vehicle_image_frontview, String vehicle_image_rearview, String vehicle_image_front_interior_view, String vehicle_image_rear_interior_view, String vehicle_driver_name, String vehicle_driver_tell, String vehicle_driver_license_rear_view, String vehicle_driver_license_front_view, String vehicle_driver_remarks, String accessUsername, String accessToken, String refreshToken);

    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token);
}
