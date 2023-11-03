/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 10:18 AM
*/
package lk.nexttravelehund.travelpackage_microservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 10:18 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "TravelPackage")
public class TravelPackage {
    @Transient
    public static final String SEQUENCE_NAME = "gtravelpackage_sequence";

    @Id
    private String id;

    private String travelpackage_name;
    private int travelpackage_total_profit;//*
    private String travelpackage_travel_areas;
    private String travelpackage_category;
    private int travelpackage_total_value;//*
    private int travelpackage_service_charge;
    private int travelpackage_travelling_length_km;
    private int travelpackage_travelling_days;
    private String travelpackage_hotel_booking_type;
    private int travelpackage_hotel_booking_nigths;
    private int travelpackage_hotel_booking_days;

    //vedio tutorial
    private String vedio_name;
    private String vedio_content;
    private String vedio_link;

    //life insuarance
    private String insuarance_name;
    private String insuarance_description;
    private String insuarance_policies;
    private int insuarance_covergae_value;

    //promotions
    private String promotion_name;
    private String promotion_content;
    private String promotion_rate;
    private String promotion_start_date;
    private String promotion_end_date;
}
