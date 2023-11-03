/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 1:22 PM
*/
package lk.nexttravel.vehicle_microservice.service;

import lk.nexttravel.vehicle_microservice.dto.ReqVehicleSaveDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 1:22 PM
 */
public interface VehicleService {
    ResponseEntity<String> SaveNewVehicel(ReqVehicleSaveDTO reqVehicleSaveDTO);
}
