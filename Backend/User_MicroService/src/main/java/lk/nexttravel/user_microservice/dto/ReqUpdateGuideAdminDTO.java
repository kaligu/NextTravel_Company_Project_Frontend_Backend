/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 9:03 AM
*/
package lk.nexttravel.user_microservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 9:03 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqUpdateGuideAdminDTO {
    private String id;
    private String name_with_initial;
    private String nic_or_passport;
    private String address;
    private String profile_image;

    //---for security purpose
    private String token;
}
