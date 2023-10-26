package lk.nexttravel.api_gateway.dto.user;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 2:54 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString

@Component
public class UserReqNewClientSaveDTO {
    @Id
    private String id;
    private String name_with_initial;
    private String address;
    private String profile_image;
    private String nic_or_passport;

    //---for security purpose
    private String token;
}
