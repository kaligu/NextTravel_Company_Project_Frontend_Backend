/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/1/2023
  @ Time         : 9:47 PM
*/
package lk.nexttravel.hotel_microservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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
    @Transient
    public static final String SEQUENCE_NAME = "hotels_sequence";

    @Id
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

    //for 2PC transaction
    private String transaction_state;


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
