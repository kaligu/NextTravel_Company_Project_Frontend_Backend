/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 11:14 AM
*/
package lk.nexttravel.api_gateway.entity.util;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 11:14 AM
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder

@Document(collection = "database_sequences")
@Component
public class DatabaseSequence {
    @Id
    private String id;

    private long seq;
}
