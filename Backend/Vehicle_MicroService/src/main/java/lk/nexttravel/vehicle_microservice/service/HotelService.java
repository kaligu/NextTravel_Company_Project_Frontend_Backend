/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:24 AM
*/
package lk.nexttravel.vehicle_microservice.service;

import lk.nexttravel.hotel_microservice.dto.ReqHotelSaveDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:24 AM
 */
public interface HotelService {
    ResponseEntity<String> SaveNewHotel(ReqHotelSaveDTO reqNewGuideSaveDTO);
}
