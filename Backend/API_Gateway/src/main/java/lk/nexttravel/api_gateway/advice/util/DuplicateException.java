package lk.nexttravel.api_gateway.advice.util;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 4:51 PM
 */

public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
