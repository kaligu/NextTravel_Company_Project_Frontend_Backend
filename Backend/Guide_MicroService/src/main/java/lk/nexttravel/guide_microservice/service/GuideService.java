/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 5:35 AM
*/
package lk.nexttravel.guide_microservice.service;

import lk.nexttravel.guide_microservice.dto.ReqNewGuideSaveDTO;
import org.springframework.http.ResponseEntity;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 5:35 AM
 */
public interface GuideService {
    ResponseEntity<String> SaveNewGuide_Prepare(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<String> SaveNewGuide_Commit(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<String> SaveNewGuide_Abrot(ReqNewGuideSaveDTO reqNewGuideSaveDTO);
}
