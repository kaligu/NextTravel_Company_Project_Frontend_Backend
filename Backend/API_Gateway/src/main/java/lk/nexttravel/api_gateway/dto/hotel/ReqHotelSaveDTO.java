/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:27 AM
*/
package lk.nexttravel.api_gateway.dto.hotel;

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

    //option1 1
    private String option_1_description;
    private String option_1_name;
    private String option_1_fee;

    //option1 2
    private String option_2_description;
    private String option_2_name;
    private String option_2_fee;

    //option1 3
    private String option_3_description;
    private String option_3_name;
    private String option_3_fee;

    //option1 4
    private String option_4_description;
    private String option_4_name;
    private String option_4_fee;
}
