/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 5:01 AM
*/
package lk.nexttravel.api_gateway.dto.auth;

import lk.nexttravel.api_gateway.util.RoleTypes;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 5:01 AM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalFrontendSecurityCheckDTO {
    private String username;
    private String access_token;
    private String refresh_token;
    private boolean accesssible;
    private RoleTypes role;
}
