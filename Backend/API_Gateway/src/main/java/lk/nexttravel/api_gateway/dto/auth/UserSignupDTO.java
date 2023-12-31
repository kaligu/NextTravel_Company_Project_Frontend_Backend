/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 6:57 AM
*/
package lk.nexttravel.api_gateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 6:57 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class UserSignupDTO {
    private String signup_name;
    private String signup_name_with_initial;
    private String signup_email;
    private String signup_password;
    private String signup_nic_or_passport;
    private String signup_address;
    private String signup_profile_image;

}
