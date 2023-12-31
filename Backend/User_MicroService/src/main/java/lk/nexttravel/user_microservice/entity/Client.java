/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 7:41 AM
*/
package lk.nexttravel.user_microservice.entity;

import lk.nexttravel.user_microservice.util.GenderTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 7:41 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "Client")
public class Client {
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String profile_image;
    private String nic_or_passport;
    private String nic_or_passport_front_view;
    private String nic_or_passport_rear_view;
    private GenderTypes gender;
    private long Tell;
    private String Remarks;
    private int age;

    //for 2PC transaction
    private String transaction_state;
}
