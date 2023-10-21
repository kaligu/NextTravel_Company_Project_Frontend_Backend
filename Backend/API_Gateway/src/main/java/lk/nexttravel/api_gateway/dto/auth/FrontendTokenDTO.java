/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 1:39 PM
*/
package lk.nexttravel.api_gateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 1:39 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class FrontendTokenDTO {
    private String access_username;
    private String access_jwt_token;
    private String access_refresh_token;
}
