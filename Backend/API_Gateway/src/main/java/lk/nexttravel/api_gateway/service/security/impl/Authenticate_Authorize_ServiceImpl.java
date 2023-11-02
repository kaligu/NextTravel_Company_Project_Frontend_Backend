/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 4:56 AM
*/
package lk.nexttravel.api_gateway.service.security.impl;

import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalJWTUserDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalRefreshTUserDTO;
import lk.nexttravel.api_gateway.service.security.Authenticate_Authorize_Service;
import lk.nexttravel.api_gateway.service.security.util.APIGatewayJwtAccessTokenServiceFrontend;
import lk.nexttravel.api_gateway.service.security.util.RefreshTokenServiceFrontend;
import lk.nexttravel.api_gateway.util.RoleTypes;
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

    @Autowired
    InternalFrontendSecurityCheckDTO internalFrontendSecurityCheckDTO;

    @Override
    public InternalFrontendSecurityCheckDTO validateRequestsAndGetMetaData(FrontendTokenDTO frontendTokenDTO) {

        InternalJWTUserDTO internalJWTUserDTO = this.apiGatewayJwtAccessTokenServiceFrontend.validateUpdateGetUserJWT(frontendTokenDTO.getAccess_jwt_token(), frontendTokenDTO.getAccess_username());

        InternalRefreshTUserDTO internalRefreshTUserDTO = this.refreshTokenServiceFrontend.validateUpdateGetUserJWT(frontendTokenDTO.getAccess_refresh_token(), frontendTokenDTO.getAccess_username());

        if (internalJWTUserDTO.isUserAuthorized() && internalRefreshTUserDTO.isUserAuthenticated()) {
            internalFrontendSecurityCheckDTO.setUsername(frontendTokenDTO.getAccess_username());
            internalFrontendSecurityCheckDTO.setAccess_token(internalJWTUserDTO.getAccessToken());
            internalFrontendSecurityCheckDTO.setRefresh_token(internalRefreshTUserDTO.getRefreshToken());
            internalFrontendSecurityCheckDTO.setRole(internalJWTUserDTO.getRole());
            internalFrontendSecurityCheckDTO.setAccesssible(true);
            return internalFrontendSecurityCheckDTO;
        } else {
            internalFrontendSecurityCheckDTO.setUsername((String)null);
            internalFrontendSecurityCheckDTO.setAccess_token((String)null);
            internalFrontendSecurityCheckDTO.setRole((RoleTypes)null);
            internalFrontendSecurityCheckDTO.setRefresh_token((String)null);
            internalFrontendSecurityCheckDTO.setAccesssible(false);
            return internalFrontendSecurityCheckDTO;
        }
    }
}
