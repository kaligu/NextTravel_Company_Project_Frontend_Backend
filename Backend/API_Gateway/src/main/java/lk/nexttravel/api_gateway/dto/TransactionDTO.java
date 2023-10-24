/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/24/2023
  @ Time         : 12:41 PM
*/
package lk.nexttravel.api_gateway.dto;

import lombok.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/24/2023
 * Time    : 12:41 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class TransactionDTO {
    private String url;
    private HttpMethod httpMethod;
    private Object data;
}
