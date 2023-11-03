package lk.nexttravelehund.travelpackage_microservice.dto;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:47 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class RespondDTO {
    private String rspd_code;
    private String repd_msg;
    private Object token;
    private Object data;
}
