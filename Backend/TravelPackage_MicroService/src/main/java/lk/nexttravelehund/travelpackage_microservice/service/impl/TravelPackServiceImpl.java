/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 6:41 PM
*/
package lk.nexttravelehund.travelpackage_microservice.service.impl;


import lk.nexttravelehund.travelpackage_microservice.dto.ReqNewTravelPackageDTO;
import lk.nexttravelehund.travelpackage_microservice.entity.TravelPackage;
import lk.nexttravelehund.travelpackage_microservice.persistence.TravelPackRepository;
import lk.nexttravelehund.travelpackage_microservice.service.SequenceGeneratorService;
import lk.nexttravelehund.travelpackage_microservice.service.TravelPackService;
import lk.nexttravelehund.travelpackage_microservice.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravelehund.travelpackage_microservice.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 6:41 PM
 */

@Service
public class TravelPackServiceImpl implements TravelPackService {
    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private TravelPackRepository travelPackRepository;

    @Override
    public ResponseEntity<String> saveNewTravelPack(ReqNewTravelPackageDTO reqNewGuideSaveDTO) {
        String id = "T00"+sequenceGeneratorService.generateSequence(TravelPackage.SEQUENCE_NAME);
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //save into database
                travelPackRepository.save(
                        TravelPackage.builder()
                                .id(id)
                                .travelpackage_name(reqNewGuideSaveDTO.getTravelpackage_name())
                                .travelpackage_travel_areas(reqNewGuideSaveDTO.getTravelpackage_travel_areas())
                                .travelpackage_category(reqNewGuideSaveDTO.getTravelpackage_category())
                                .travelpackage_service_charge(reqNewGuideSaveDTO.getTravelpackage_service_charge())
                                .travelpackage_travelling_length_km(reqNewGuideSaveDTO.getTravelpackage_travelling_length_km())
                                .travelpackage_travelling_days(reqNewGuideSaveDTO.getTravelpackage_travelling_days())
                                .travelpackage_hotel_booking_type(reqNewGuideSaveDTO.getTravelpackage_hotel_booking_type())
                                .travelpackage_hotel_booking_nigths(reqNewGuideSaveDTO.getTravelpackage_hotel_booking_nigths())
                                .travelpackage_hotel_booking_days(reqNewGuideSaveDTO.getTravelpackage_hotel_booking_days())
                                .vedio_name(reqNewGuideSaveDTO.getVedio_name())
                                .vedio_content(reqNewGuideSaveDTO.getVedio_content())
                                .vedio_link(reqNewGuideSaveDTO.getVedio_link())
                                .insuarance_name(reqNewGuideSaveDTO.getInsuarance_name())
                                .insuarance_description(reqNewGuideSaveDTO.getInsuarance_description())
                                .insuarance_policies(reqNewGuideSaveDTO.getInsuarance_policies())
                                .insuarance_covergae_value(reqNewGuideSaveDTO.getInsuarance_covergae_value())
                                .promotion_name(reqNewGuideSaveDTO.getPromotion_name())
                                .promotion_content(reqNewGuideSaveDTO.getPromotion_content())
                                .promotion_rate(reqNewGuideSaveDTO.getPromotion_rate())
                                .promotion_start_date(reqNewGuideSaveDTO.getPromotion_start_date())
                                .promotion_end_date(reqNewGuideSaveDTO.getPromotion_end_date())
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
