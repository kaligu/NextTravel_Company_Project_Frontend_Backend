/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:25 AM
*/
package lk.nexttravel.hotel_microservice.service.impl;

import lk.nexttravel.hotel_microservice.dto.ReqHotelSaveDTO;
import lk.nexttravel.hotel_microservice.service.HotelService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:25 AM
 */

@Service
public class HotelServiceImpl implements HotelService {
    @Override
    public ResponseEntity<String> SaveNewHotel(ReqHotelSaveDTO reqNewGuideSaveDTO) {
        return null;
    }
}
