/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 9:03 AM
*/
package lk.nexttravel.api_gateway.dto.user;

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
    private String signup_name_with_initial;
    private String nic_or_passport;
    private String address;
    private String profile_image;
    private String transaction_state;

    //---for security purpose
    private String token;
}
