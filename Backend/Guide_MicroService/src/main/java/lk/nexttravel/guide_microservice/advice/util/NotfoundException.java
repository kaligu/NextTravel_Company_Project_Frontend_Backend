package lk.nexttravel.guide_microservice.advice.util;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/20/2023
 * Time    : 12:47 PM
 */
public class NotfoundException extends RuntimeException{
    public NotfoundException(String message){
        super(message);
    }
}
