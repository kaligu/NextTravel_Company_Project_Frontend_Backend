/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 9:32 AM
*/
package lk.nexttravel.vehicle_microservice.entity.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 9:32 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "Vehicle")
public class Vehicle {
    @Transient
    public static final String SEQUENCE_NAME = "hotels_sequence";

    @Id
    private String vehicle_id;
    private String vehicle_type;
    private String vehicle_fuel_type;
    private String vehicle_hybrid_or_non_hybrid;
    private String vehicle_seat_capacity;
    private String vehicle_transmission_type;
    private String vehicle_fuel_usage;
    private String vehicle_perday_vehicle_fee;
    private String vehicle_category;

    //image
    private String vehicle_image_sideview;
    private String vehicle_image_frontview;
    private String vehicle_image_rearview;
    private String vehicle_image_front_interior_view;
    private String vehicle_image_rear_interior_view;

    //driver
    private String vehicle_driver_name;
    private String vehicle_driver_tell;
    private String vehicle_driver_license_rear_view;
    private String vehicle_driver_license_front_view;
    private String vehicle_driver_remarks;

}
