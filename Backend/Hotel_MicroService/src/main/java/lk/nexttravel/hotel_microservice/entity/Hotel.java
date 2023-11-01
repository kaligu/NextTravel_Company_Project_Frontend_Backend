/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/1/2023
  @ Time         : 9:47 PM
*/
package lk.nexttravel.hotel_microservice.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/1/2023
 * Time    : 9:47 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "Hotel")
public class Hotel {
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
}
