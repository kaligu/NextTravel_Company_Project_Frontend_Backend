/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/20/2023
  @ Time         : 5:00 AM
*/
package lk.nexttravel.vehicle_microservice.util;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 5:00 AM
 */
public class RespondCodes {

    public static final String Respond_SUCCESS = "00";
    public static final String Respond_NO_DATA_FOUND = "01";
    public static final String Respond_NOT_AUTHORISED = "02";
    public static final String Respond_TOKEN_EXPIRED = "03";
    public static final String Respond_TOKEN_INVALID = "04";
    public static final String Respond_ERROR = "05";
    public static final String Respond_DUPLICATED = "06";
    public static final String Respond_FAIL = "10";
    public static final String Respond_SERVERSIDE_INTERNAL_FAIL = "11";
    public static final String Respond_DATA_INVALID = "12";
    public static final String Respond_AUTHORISED = "13";

    public static final String Respond_DATA_SAVED = "14";
    public static final String Respond_DATA_DELETED = "15";

    public static final String Respond_DATA_NOT_SAVED = "16";

    public static final String Respond_New_Tokens_Generated = "C100";

    public static final String PENDING = "PENDING";
    public static final String COMMITED = "COMMITED";

}
