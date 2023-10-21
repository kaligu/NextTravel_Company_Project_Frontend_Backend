package lk.nexttravel.api_gateway.advice;

import lk.nexttravel.api_gateway.advice.util.*;
import lk.nexttravel.api_gateway.dto.RespondDTO;
import lk.nexttravel.api_gateway.util.RespondCodes;
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
    @ExceptionHandler(DuplicateException.class)
    protected ResponseEntity<RespondDTO> exception(DuplicateException exception) {
        System.out.println("DuplicateException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Response_DUPLICATED,exception.getMessage(), null,null)
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<RespondDTO> exception(InternalServerException exception) {
        System.out.println("InternalServerException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Response_SERVERSIDE_INTERNAL_FAIL,exception.getMessage(), null,null)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidInputException.class)
    protected ResponseEntity<RespondDTO> exception(InvalidInputException exception) {
        System.out.println("InvalidInputException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Response_DATA_INVALID,exception.getMessage(), null,null)
                , HttpStatus.BAD_REQUEST);
    }

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

    @ExceptionHandler(PasswordNotMatchException.class)
    protected ResponseEntity<RespondDTO> exception(PasswordNotMatchException passwordNotMatchException) {
        return new ResponseEntity<RespondDTO>(
                (RespondDTO.builder()
                        .rspd_code(RespondCodes.Response_NOT_AUTHORISED)
                        .repd_msg(passwordNotMatchException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.UNAUTHORIZED
        );
    }
}
