/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 5:00 AM
*/
package lk.nexttravel.user_microservice.util;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 5:00 AM
 */
public class RespondCodes {
    //Backen Api gateway to Frontend Respond codes
    public static final String FrontRespond_SUCCESS              = "1000";

    //Backen Microservice to Api gateway Respond codes
    public static final String BackRespond_SUCCESS              = "2000";

    public static final String Response_SUCCESS              = "00";
    public static final String Response_NO_DATA_FOUND        = "01";
    public static final String Response_NOT_AUTHORISED       = "02";
    public static final String Response_TOKEN_EXPIRED        = "03";
    public static final String Response_TOKEN_INVALID        = "04";
    public static final String Response_ERROR                = "05";
    public static final String Response_DUPLICATED           = "06";
    public static final String Response_FAIL                 = "10";
    public static final String Response_SERVERSIDE_INTERNAL_FAIL      = "11";
    public static final String Response_DATA_INVALID        = "12";
    public static final String Response_AUTHORISED       = "13";
    public static final String Response_DATA_SAVED         = "14";

    public static final String Response_New_Tokens_Generated        = "C100";

}
