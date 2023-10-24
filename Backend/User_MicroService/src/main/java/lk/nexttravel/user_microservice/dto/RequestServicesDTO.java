/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/24/2023
  @ Time         : 2:00 PM
*/
package lk.nexttravel.user_microservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/24/2023
 * Time    : 2:00 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class RequestServicesDTO {
    private String transactionphase;
    private Object data;
}
