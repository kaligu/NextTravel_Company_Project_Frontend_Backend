/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 11:05 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.service.UserService;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.api_gateway.util.RespondCodes;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.RqRpURLs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 11:05 PM
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    Authenticate_Authorize_Service authenticate_authorize_service;

    @Autowired
    APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Override
    public ResponseEntity<RespondDTO> UserAdminGetProfileImage(FrontendTokenDTO frontendTokenDTO) {
        try{
            InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO = authenticate_authorize_service.validateRequestsAndGetMetaData(frontendTokenDTO);
            if(
                    internalFrontendSecurityCheckDTO.isAccesssible()
                            &&
                            internalFrontendSecurityCheckDTO.getRole().equals(RoleTypes.ROLE_ADMIN_SERVICE_USER)
            ) {
                //get data using restcontroller
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                HttpEntity<String> entity = new HttpEntity<>(null, headers); // Sending an empty body

                ResponseEntity<String> responseEntity = restTemplate.exchange(
                        "http://localhost:1020/api/admin/user-admin-get-profile-image?username=" + frontendTokenDTO.getAccess_username() + "&token=" + apiGatewayJwtAccessTokenServiceBackend.generateToken(),
                        HttpMethod.GET,
                        entity,
                        String.class
                );
                String responseBody = responseEntity.getBody();
                System.out.println(responseBody);
                return new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .token(
                                        FrontendTokenDTO.builder()
                                                .access_username(internalFrontendSecurityCheckDTO.getUsername())
                                                .access_jwt_token(internalFrontendSecurityCheckDTO.getAccess_token())
                                                .access_refresh_token(internalFrontendSecurityCheckDTO.getRefresh_token())
                                                .build()
                                )
                                .build()
                        ,
                        HttpStatus.OK
                );
            }else {
                return  new ResponseEntity<RespondDTO>(
                        RespondDTO.builder()
                                .rspd_code(RespondCodes.Respond_NOT_AUTHORISED)
                                .repd_msg("espond_NOT_AUTHORISED")
                                .token(null)
                                .data(null).build()
                        ,
                        HttpStatus.UNAUTHORIZED );
            }
        }catch (Exception e){
            throw new InternalServerException("0");
        }
    }
}
