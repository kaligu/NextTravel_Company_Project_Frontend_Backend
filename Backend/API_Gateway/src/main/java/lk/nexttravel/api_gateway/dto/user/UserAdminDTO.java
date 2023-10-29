/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/29/2023
  @ Time         : 9:08 PM
*/
package lk.nexttravel.api_gateway.dto.user;

import lk.nexttravel.api_gateway.util.RoleTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/29/2023
 * Time    : 9:08 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class UserAdminDTO {

    private String id;

    private String name;  //uniq

    private String email;

    private RoleTypes role_type;

    private String signup_name_with_initial;

    private String nic_or_passport;

    private String address;

    private long salary;

    private String profile_image;
}
