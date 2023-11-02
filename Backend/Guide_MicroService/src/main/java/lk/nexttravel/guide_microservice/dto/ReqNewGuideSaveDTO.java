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
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String profile_image;
    private String nic_or_passport;

    //---for security purpose
    private String token;
}
