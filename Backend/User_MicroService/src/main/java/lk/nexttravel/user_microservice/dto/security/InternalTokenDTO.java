/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/22/2023
  @ Time         : 3:13 AM
*/
package lk.nexttravel.user_microservice.dto.security;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/22/2023
 * Time    : 3:13 AM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class InternalTokenDTO {
   private String token;
   private boolean authenticated;
}
