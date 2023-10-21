package lk.nexttravel.user_microservice.advice;

import lk.nexttravel.user_microservice.advice.util.NotfoundException;
import lk.nexttravel.user_microservice.dto.RespondDTO;
import lk.nexttravel.user_microservice.util.RespondCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 20/7/2023
 * Time    : 4:49 PM
 */

@ControllerAdvice
public class RespondExceptionHandler {

    @ExceptionHandler(NotfoundException.class)
    protected ResponseEntity<RespondDTO> exception(NotfoundException notfoundException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_NO_DATA_FOUND)
                        .rspd_code(notfoundException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.NOT_FOUND
        );
    }

}
