/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:27 AM
*/
package lk.nexttravel.vehicle_microservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:27 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqHotelSaveDTO {

    private String id;
    private String location;
    private String location_coordinates;
    private int category_stars;
    private String name;
    private String remarks;
    private int perday_hotel_fee_for_days;
    private int perday_hotel_fee_for_nights;
    private String email;
    private boolean is_pet_allowed;
    private int cancellation_fee;
    private String tell_1;
    private String tell_2;
    private String reference_link;
    private String image;

    //---for security purpose
    private String token;
}
