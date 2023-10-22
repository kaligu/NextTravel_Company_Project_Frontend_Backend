/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/23/2023
  @ Time         : 12:29 AM
*/
package lk.nexttravel.api_gateway.dto.mail;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/23/2023
 * Time    : 12:29 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class MailDTO {
    private String mailFrom;
    private String mailTo;
    private String mailCc;
    private String mailBcc;
    private String mailSubject;
    private String mailContent;
    private String templateName;
    private String contentType;

    public MailDTO() {
        this.contentType = "text/html";
    }
}
