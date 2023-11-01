/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/1/2023
  @ Time         : 11:02 PM
*/
package lk.nexttravel.hotel_microservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/1/2023
 * Time    : 11:02 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "HotelOptions")
public class HotelOptions {
    @Id
    private String id;
    private String description;
    private String name;
    private String image;
    private String fee;

    @DBRef
    private Hotel hotel;
}
