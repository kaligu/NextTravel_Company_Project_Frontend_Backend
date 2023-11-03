/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 9:46 PM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 9:46 PM
 */
public interface TravelpackageService {
    //get user admin profile image
    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token);

    Mono<ResponseEntity<RespondDTO>> createNewTravelPack(String travelpackage_name, String travelpackage_travel_areas, String travelpackage_category, String travelpackage_service_charge, String travelpackage_travelling_length_km, String travelpackage_travelling_days, String travelpackage_hotel_booking_type, String travelpackage_hotel_booking_nigths, String travelpackage_hotel_booking_days, String vedio_name, String vedio_content, String vedio_link, String insuarance_name, String insuarance_description, String insuarance_policies, String insuarance_covergae_value, String promotion_name, String promotion_content, String promotion_rate, String promotion_start_date, String promotion_end_date, String access_username, String access_jwt_token, String access_refresh_token);
}
