/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/28/2023
  @ Time         : 6:29 PM
*/
package lk.nexttravel.api_gateway.service;

import lk.nexttravel.api_gateway.dto.RespondDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/28/2023
 * Time    : 6:29 PM
 */
public interface HotelService {
    //get user admin profile image

    Mono<ResponseEntity<RespondDTO>> UserAdminGetProfileData(String access_username, String access_jwt_token, String access_refresh_token);

    Mono<ResponseEntity<RespondDTO>> createNewHotel(String name, String location, String locationMapLink, String remarks, String referenceLink, String email, String tell1, String tell2, String categoryStar, String isPetAllowed, String perdayHotelFee, String pernightHotelFee, String cancellationFee, String image, String accessUsername, String accessToken, String refreshToken , String option_1_description, String option_1_name ,String option_1_fee, String option_2_description, String option_2_name,  String option_2_fee, String option_3_description, String option_3_name, String option_3_fee, String option_4_description, String option_4_name, String option_4_fee );
}
