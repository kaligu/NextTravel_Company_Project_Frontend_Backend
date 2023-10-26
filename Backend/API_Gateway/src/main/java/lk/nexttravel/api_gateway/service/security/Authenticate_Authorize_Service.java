/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/27/2023
  @ Time         : 4:53 AM
*/
package lk.nexttravel.api_gateway.service.security;

import lk.nexttravel.api_gateway.dto.auth.FrontendTokenDTO;
import lk.nexttravel.api_gateway.dto.auth.InternalFrontendSecurityCheckDTO;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/27/2023
 * Time    : 4:53 AM
 */
public interface Authenticate_Authorize_Service {
    InternalFrontendSecurityCheckDTO validateRequestsAndGetMetaData(FrontendTokenDTO frontendTokenDTO);
}
