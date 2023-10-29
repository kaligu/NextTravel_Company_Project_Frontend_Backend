/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:43 AM
*/
package lk.nexttravel.api_gateway.util.security;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:43 AM
 */
public class SecurityCodes {

    //API Gateway Token Details - For Frontend
    public static final String FRONTEND_APIGATEWAY_JWT_TOKEN_KEY = "FRONTENDTOKENEFGVBTHJNMYUJKEDFVBEDFGBTGHNEDFCWSDXQAZEDFCTGBHNJMYHNTGBFVEDCWSXFVFGVGBYHNTGBKJOLDFGHJRFGHJWSDCEDFVRFGBRFGBEDFV";
    public static final long FRONTEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY = 5 * 60 * 60 *60;//5min

    //API Gateway Token Details - For Backend
    public static final String BACKEND_APIGATEWAY_JWT_TOKEN_USERNAME = "API_GATEWAY_BACKEND";
    public static final String BACKEND_APIGATEWAY_JWT_TOKEN_KEY = "BACKENDTOKENERTYUIDFGHJKERTYUIDFGHHJHLHNKGMDHEYRJFGHFEHEWQYUIEORUDHGHSJDHNNCDHRIUEYIOIUWERTYUSDFGHJDFGHERTYUIDFGHJRTYUIDFGHJK";
    public static final long BACKEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY     = 5 * 60 * 60 *60; //5min
}
