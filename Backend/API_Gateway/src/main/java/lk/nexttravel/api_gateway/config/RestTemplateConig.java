/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:31 PM
*/
package lk.nexttravel.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:31 PM
 */

@Configuration
public class RestTemplateConig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
