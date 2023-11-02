/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 5:35 AM
*/
package lk.nexttravel.guide_microservice.service.impl;

import lk.nexttravel.guide_microservice.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_microservice.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 5:35 AM
 */
public class GuideServiceImpl implements GuideService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Override
    public ResponseEntity<String> SaveNewGuide_Prepare(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveNewGuide_Commit(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        return null;
    }

    @Override
    public ResponseEntity<String> SaveNewGuide_Abrot(ReqNewGuideSaveDTO reqNewGuideSaveDTO) {
        return null;
    }
}
