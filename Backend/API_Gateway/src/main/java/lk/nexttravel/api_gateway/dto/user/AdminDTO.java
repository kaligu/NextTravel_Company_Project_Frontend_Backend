/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 7:44 AM
*/
package lk.nexttravel.api_gateway.dto.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 7:44 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

public class AdminDTO {

    private String id;
    private String signup_name_with_initial;
    private String nic_or_passport;
    private String address;
    private long salary;
    private String profile_image;

    //for 2PC transaction
    private String transaction_state;
}
