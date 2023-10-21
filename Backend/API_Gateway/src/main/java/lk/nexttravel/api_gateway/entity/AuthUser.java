/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 7:58 AM
*/
package lk.nexttravel.api_gateway.entity;

import lk.nexttravel.api_gateway.util.RoleTypes;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 7:58 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

@Document(collection = "AuthUser")
public class AuthUser {

    @Id
    private String id;

    private String name;

    private String email;

    private String password;

    private int mail_otp;

    private RoleTypes role_type;
}
