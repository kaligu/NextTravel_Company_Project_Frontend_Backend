/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/24/2023
  @ Time         : 12:25 PM
*/
package lk.nexttravel.api_gateway.service.transaction;

import lk.nexttravel.api_gateway.dto.TransactionDTO;

import java.util.List;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/24/2023
 * Time    : 12:25 PM
 */
public interface TransactionCordinator {

    //For create method
    boolean preparePhaseForCreate(List<TransactionDTO> transactionDTOList);

    void commitPhaseForCreate(List<TransactionDTO> transactionDTOList);

    void rollbackPhaseForCreate(List<TransactionDTO> transactionDTOList);

    //For update method
    boolean preparePhaseForUpdate(List<TransactionDTO> transactionDTOList);

    void commitPhaseForUpdate(List<TransactionDTO> transactionDTOList);

    void rollbackPhaseForUpdate(List<TransactionDTO> transactionDTOList);

    //For delete method
    boolean preparePhaseForDelete(List<TransactionDTO> transactionDTOList);

    void commitPhaseForDelete(List<TransactionDTO> transactionDTOList);


}
