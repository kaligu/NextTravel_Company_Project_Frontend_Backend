/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 2:54 PM
*/
package lk.nexttravel.guide_microservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:54 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqNewGuideSaveDTO {
    private String name;

    private String remarks;

    private int experience;

    private String nic;

    private String nic_front_view;

    private String nic_rear_view;

    private String tell;

    private String gender;

    private Data dob;

    private String image;

    private String address;

    private int perday_fee;

    //---for security purpose
    private String token;
}
