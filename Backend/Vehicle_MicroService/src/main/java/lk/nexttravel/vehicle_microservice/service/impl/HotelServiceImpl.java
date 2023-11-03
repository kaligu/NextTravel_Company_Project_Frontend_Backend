/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:25 AM
*/
package lk.nexttravel.vehicle_microservice.service.impl;

import lk.nexttravel.hotel_microservice.dto.ReqHotelSaveDTO;
import lk.nexttravel.hotel_microservice.entity.Hotel;
import lk.nexttravel.hotel_microservice.repository.HotelRepository;
import lk.nexttravel.hotel_microservice.service.HotelService;
import lk.nexttravel.hotel_microservice.service.SequenceGeneratorService;
import lk.nexttravel.hotel_microservice.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.hotel_microservice.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:25 AM
 */

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private HotelRepository hotelRepository;


    @Override
    public ResponseEntity<String> SaveNewHotel(ReqHotelSaveDTO reqHotelSaveDTO) {
        //check authentication
//        System.out.println(reqHotelSaveDTO.toString());
        String id = "H00"+sequenceGeneratorService.generateSequence(Hotel.SEQUENCE_NAME);
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqHotelSaveDTO.getToken())) {  //check gateway token
                //save into database
                hotelRepository.save(
                        Hotel.builder()
                                .id(id)
                                .location(reqHotelSaveDTO.getLocation())
                                .location_coordinates(reqHotelSaveDTO.getLocation_coordinates())
                                .category_stars(reqHotelSaveDTO.getCategory_stars())
                                .name(reqHotelSaveDTO.getName())
                                .remarks(reqHotelSaveDTO.getRemarks())
                                .perday_hotel_fee_for_nights(reqHotelSaveDTO.getPerday_hotel_fee_for_nights())
                                .perday_hotel_fee_for_days(reqHotelSaveDTO.getPerday_hotel_fee_for_days())
                                .email(reqHotelSaveDTO.getEmail())
                                .is_pet_allowed(reqHotelSaveDTO.is_pet_allowed())
                                .cancellation_fee(reqHotelSaveDTO.getCancellation_fee())
                                .tell_1(reqHotelSaveDTO.getTell_1())
                                .tell_2(reqHotelSaveDTO.getTell_2())
                                .reference_link(reqHotelSaveDTO.getReference_link())
                                .image(reqHotelSaveDTO.getImage())
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
