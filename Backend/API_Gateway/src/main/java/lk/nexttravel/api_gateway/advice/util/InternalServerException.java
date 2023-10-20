package lk.nexttravel.api_gateway.advice.util;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:50 PM
 */

public class InternalServerException extends RuntimeException{
    public InternalServerException(String message){
        super(message);
    }
}
