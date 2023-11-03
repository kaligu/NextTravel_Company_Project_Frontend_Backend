package lk.nexttravelehund.travelpackage_microservice.advice;


import lk.nexttravel.guide_microservice.advice.util.InternalServerException;
import lk.nexttravel.guide_microservice.advice.util.NotfoundException;
import lk.nexttravel.guide_microservice.dto.RespondDTO;
import lk.nexttravel.guide_microservice.util.RespondCodes;
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
                        .rspd_code(RespondCodes.Respond_NO_DATA_FOUND)
                        .rspd_code(notfoundException.getMessage())
                        .token(null)
                        .data(null)
                        .build()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InternalServerException.class)
    protected ResponseEntity<RespondDTO> exception(InternalServerException exception) {
        System.out.println("InternalServerException"+exception.getMessage());
        return new ResponseEntity<>(
                new RespondDTO(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL,exception.getMessage(), null,null)
                , HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
