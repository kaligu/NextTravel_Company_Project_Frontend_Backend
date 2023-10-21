/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 11:24 AM
*/
package lk.nexttravel.api_gateway.dto.auth;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 11:24 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Component
public class InternalRefreshTUserDTO {
    private String RefreshToken;
    private boolean isUserAuthenticated;
}
