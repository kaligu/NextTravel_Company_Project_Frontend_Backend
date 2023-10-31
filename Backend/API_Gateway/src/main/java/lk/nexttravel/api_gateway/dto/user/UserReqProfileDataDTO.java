/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 11:49 AM
*/
package lk.nexttravel.api_gateway.dto.user;

import lk.nexttravel.api_gateway.util.RoleTypes;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 11:49 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class UserReqProfileDataDTO {

    private String name;

    private String email;

    private String name_with_initial;

    private String nic_or_passport;

    private String address;

    private String profile_image;
}
