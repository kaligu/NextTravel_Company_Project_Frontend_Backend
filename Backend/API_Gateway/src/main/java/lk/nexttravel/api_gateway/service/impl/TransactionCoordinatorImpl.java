/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/24/2023
  @ Time         : 12:25 PM
*/
package lk.nexttravel.api_gateway.service.impl;

import lk.nexttravel.api_gateway.dto.TransactionDTO;
import lk.nexttravel.api_gateway.dto.user.UserReqNewClientSaveDTO;
import lk.nexttravel.api_gateway.service.TransactionCordinator;
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
    public void commitPhaseForCreate(List<TransactionDTO> transactionDTOList) {
        for (TransactionDTO transactionDTO : transactionDTOList){
            sendToCommit(transactionDTO);
        }
    }

    @Override
    public void rollbackPhaseForCreate(List<TransactionDTO> transactionDTOList) {
        for (TransactionDTO transactionDTO : transactionDTOList){
            sendToDelete(transactionDTO);
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
    }

    private void sendToCommit(TransactionDTO data){

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
    }

    private void sendToDelete(TransactionDTO data){
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
    }
}
