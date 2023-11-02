/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 8:47 PM
*/
package lk.nexttravel.api_gateway.dto.guide;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 8:47 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class GuideDTO {

    private String id;

    private String name;

    private String remarks;

    private int experience;

    private String nic;

    private String nic_front_view;

    private String nic_rear_view;

    private String tell;

    private String gender;

    private String dob;

    private String image;

    private String address;

    private int perday_fee;

    //for 2PC transaction
    private String transaction_state;
}
