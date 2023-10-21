/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:55 AM
*/
package lk.nexttravel.api_gateway.dto.auth;

import lk.nexttravel.api_gateway.util.RoleTypes;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:55 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalJWTUserDTO {
    private String Username;
    private RoleTypes Role;
    private String AccessToken;
    private boolean isUserAuthorized;
}
