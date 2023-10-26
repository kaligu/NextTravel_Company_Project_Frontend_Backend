/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 4:56 AM
*/
package lk.nexttravel.api_gateway.service.security.impl;

import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceFrontend;
import lk.nexttravel.api_gateway.service.security.util.RefreshTokenServiceFrontend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 4:56 AM
 */
@Service
public class Authenticate_Authorize_ServiceImpl implements Authenticate_Authorize_Service {

    @Autowired
    RefreshTokenServiceFrontend refreshTokenServiceFrontend;
    @Autowired
    APIGatewayJwtAccessTokenServiceFrontend apiGatewayJwtAccessTokenServiceFrontend;

    @Override
    public InternalFrontendSecurityCheckDTO validateRequestsAndGetMetaData(FrontendTokenDTO frontendTokenDTO) {

        return null;
    }
}
