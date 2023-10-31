/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 8:47 PM
*/
package lk.nexttravel.guide_microservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 8:47 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "Guide")
public class Guide {

    @Id
    private String id;

    private String name;

    private String remarks;

    private int experience;

    private String nic_front_view;

    private String nic_rear_view;

    private String tell;

    private String grnder;

    private int age;

    private String image;

    private String address;

    private int perday_fee;
}
