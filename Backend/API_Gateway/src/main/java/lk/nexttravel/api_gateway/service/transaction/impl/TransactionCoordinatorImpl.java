/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/24/2023
  @ Time         : 12:25 PM
*/
package lk.nexttravel.api_gateway.service.transaction.impl;

import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.dto.TransactionDTO;
import lk.nexttravel.api_gateway.service.transaction.TransactionCordinator;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/24/2023
 * Time    : 12:25 PM
 */

@Service
public class TransactionCoordinatorImpl implements TransactionCordinator {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean preparePhaseForCreate(List<TransactionDTO> transactionDTOList) {
        try {
            boolean allResponsesSuccessful = true; // Assume all responses are successful by default

            for (TransactionDTO transactionDTO : transactionDTOList) {
                boolean responseSuccessful = sendToCreate(transactionDTO);

                if (!responseSuccessful) {
                    allResponsesSuccessful = false;
                    break; // Exit the loop early if any response is not successful
                }
            }

            return allResponsesSuccessful;
        }catch (Exception e){
            throw new InternalServerException("cc");
        }

    }

    @Override
    public void commitPhaseForCreate(List<TransactionDTO> transactionDTOList) {
        try {
            for (TransactionDTO transactionDTO : transactionDTOList){
                sendToCommit(transactionDTO);
            }
        }catch (Exception e){
            throw new InternalServerException("cc");
        }

    }

    @Override
    public void rollbackPhaseForCreate(List<TransactionDTO> transactionDTOList) {
        try {
            for (TransactionDTO transactionDTO : transactionDTOList){
                sendToDelete(transactionDTO);
            }
        }catch (Exception e){
            throw new InternalServerException("cc");
        }

    }

    @Override
    public boolean preparePhaseForUpdate(List<TransactionDTO> transactionDTOList) {
        boolean allResponsesSuccessful = true; // Assume all responses are successful by default

        for (TransactionDTO transactionDTO : transactionDTOList) {
            boolean responseSuccessful = sendToCreate(transactionDTO);

            if (!responseSuccessful) {
                allResponsesSuccessful = false;
                break; // Exit the loop early if any response is not successful
            }
        }

        return allResponsesSuccessful;
    }

    @Override
    public void commitPhaseForUpdate(List<TransactionDTO> transactionDTOList) {
        for (TransactionDTO transactionDTO : transactionDTOList){
            sendToCommit(transactionDTO);
        }
    }

    @Override
    public void rollbackPhaseForUpdate(List<TransactionDTO> transactionDTOList) {
        for (TransactionDTO transactionDTO : transactionDTOList){
            sendToDelete(transactionDTO);
        }
    }

    @Override
    public boolean preparePhaseForDelete(List<TransactionDTO> transactionDTOList) {
        boolean allResponsesSuccessful = true; // Assume all responses are successful by default

        for (TransactionDTO transactionDTO : transactionDTOList) {
            boolean responseSuccessful = sendToCreate(transactionDTO);

            if (!responseSuccessful) {
                allResponsesSuccessful = false;
                break; // Exit the loop early if any response is not successful
            }
        }

        return allResponsesSuccessful;
    }

    @Override
    public void commitPhaseForDelete(List<TransactionDTO> transactionDTOList) {
        for (TransactionDTO transactionDTO : transactionDTOList){
            sendToDelete(transactionDTO);
        }
    }

    private boolean sendToCreate(TransactionDTO data){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    data.getUrl(),
                    HttpMethod.POST,
                    new HttpEntity<Object> (
                            data.getData()
                            ,
                            headers
                    ),
                    String.class
            );

            return responseEntity.getStatusCode()==HttpStatus.CREATED; //if done return true
        }catch (Exception e){
            return false;
        }

    }

    private void sendToCommit(TransactionDTO data){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    data.getUrl(),
                    HttpMethod.PUT,
                    new HttpEntity<Object> (
                            data.getData()
                            ,
                            headers
                    ),
                    String.class
            );
        }catch (Exception e){
            System.out.println("eror");
        }

    }

    private void sendToDelete(TransactionDTO data){
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    data.getUrl(),
                    HttpMethod.DELETE,
                    new HttpEntity<Object> (
                            data.getData()
                            ,
                            headers
                    ),
                    String.class
            );
        }catch (Exception e){
            System.out.println("eror");
        }

    }
}
