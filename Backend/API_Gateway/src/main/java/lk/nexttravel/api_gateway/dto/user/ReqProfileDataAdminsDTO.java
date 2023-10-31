/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 1:02 PM
*/
package lk.nexttravel.api_gateway.dto.user;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 1:02 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class ReqProfileDataAdminsDTO {

    private String name_with_initial;

    private String nic_or_passport;

    private String address;

    private String profile_image;
}
