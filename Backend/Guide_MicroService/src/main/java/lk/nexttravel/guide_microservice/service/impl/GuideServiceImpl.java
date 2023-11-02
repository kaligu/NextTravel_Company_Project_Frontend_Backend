/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 5:35 AM
*/
package lk.nexttravel.guide_microservice.service.impl;

import lk.nexttravel.guide_microservice.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_microservice.entity.Guide;
import lk.nexttravel.guide_microservice.persistence.GuideRepository;
import lk.nexttravel.guide_microservice.service.GuideService;
import lk.nexttravel.guide_microservice.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.guide_microservice.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 5:35 AM
 */
@Service
public class GuideServiceImpl implements GuideService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private GuideRepository guideRepository;

    @Override
    public ResponseEntity<String> SaveNewGuide_Prepare(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        //check authentication
        System.out.println(reqNewGuideSaveDTO.getName());
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //save into database
                guideRepository.save(
                        Guide.builder()
                                .id(reqNewGuideSaveDTO.getId())
                                .name(reqNewGuideSaveDTO.getName())
                                .remarks(reqNewGuideSaveDTO.getRemarks())
                                .experience(reqNewGuideSaveDTO.getExperience())
                                .nic(reqNewGuideSaveDTO.getNic())
                                .nic_front_view(reqNewGuideSaveDTO.getNic_front_view())
                                .nic_rear_view(reqNewGuideSaveDTO.getNic_rear_view())
                                .tell(reqNewGuideSaveDTO.getTell())
                                .gender(reqNewGuideSaveDTO.getGender())
                                .dob(reqNewGuideSaveDTO.getAge())
                                .image(reqNewGuideSaveDTO.getImage())
                                .address(reqNewGuideSaveDTO.getAddress())
                                .perday_fee(reqNewGuideSaveDTO.getPerday_fee())
                                .transaction_state(RespondCodes.PENDING)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> SaveNewGuide_Commit(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //save into database
                guideRepository.save(
                        Guide.builder()
                                .id(reqNewGuideSaveDTO.getId())
                                .name(reqNewGuideSaveDTO.getName())
                                .remarks(reqNewGuideSaveDTO.getRemarks())
                                .experience(reqNewGuideSaveDTO.getExperience())
                                .nic(reqNewGuideSaveDTO.getNic())
                                .nic_front_view(reqNewGuideSaveDTO.getNic_front_view())
                                .nic_rear_view(reqNewGuideSaveDTO.getNic_rear_view())
                                .tell(reqNewGuideSaveDTO.getTell())
                                .gender(reqNewGuideSaveDTO.getGender())
                                .dob(reqNewGuideSaveDTO.getAge())
                                .image(reqNewGuideSaveDTO.getImage())
                                .address(reqNewGuideSaveDTO.getAddress())
                                .perday_fee(reqNewGuideSaveDTO.getPerday_fee())
                                .transaction_state(RespondCodes.COMMITED)
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_SAVED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> SaveNewGuide_Abrot(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        //check authentication
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqNewGuideSaveDTO.getToken())) {  //check gateway token
                //delete
                guideRepository.delete(
                        Guide.builder()
                                .id(reqNewGuideSaveDTO.getId())
                                .name(reqNewGuideSaveDTO.getName())
                                .remarks(reqNewGuideSaveDTO.getRemarks())
                                .experience(reqNewGuideSaveDTO.getExperience())
                                .nic(reqNewGuideSaveDTO.getNic())
                                .nic_front_view(reqNewGuideSaveDTO.getNic_front_view())
                                .nic_rear_view(reqNewGuideSaveDTO.getNic_rear_view())
                                .tell(reqNewGuideSaveDTO.getTell())
                                .gender(reqNewGuideSaveDTO.getGender())
                                .dob(reqNewGuideSaveDTO.getAge())
                                .image(reqNewGuideSaveDTO.getImage())
                                .address(reqNewGuideSaveDTO.getAddress())
                                .perday_fee(reqNewGuideSaveDTO.getPerday_fee())
                                .build()
                );

                return new ResponseEntity<>(RespondCodes.Respond_DATA_DELETED, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(RespondCodes.Respond_NOT_AUTHORISED, HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            return new ResponseEntity<>(RespondCodes.Respond_SERVERSIDE_INTERNAL_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
