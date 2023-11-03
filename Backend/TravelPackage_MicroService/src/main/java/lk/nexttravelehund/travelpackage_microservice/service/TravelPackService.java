/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 6:41 PM
*/
package lk.nexttravelehund.travelpackage_microservice.service;

import lk.nexttravelehund.travelpackage_microservice.dto.ReqNewTravelPackageDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 6:41 PM
 */
public interface TravelPackService {
    ResponseEntity<String> saveNewTravelPack(ReqNewTravelPackageDTO reqNewGuideSaveDTO);
}
