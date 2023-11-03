/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 5:35 AM
*/
package lk.nexttravelehund.travelpackage_microservice.service;

import lk.nexttravel.guide_microservice.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_microservice.entity.Guide;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 5:35 AM
 */
public interface GuideService {
    ResponseEntity<String> SaveNewGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<ArrayList<Guide>> getAllGuides(String token);

    ResponseEntity<String> updateGuide(ReqNewGuideSaveDTO reqNewGuideSaveDTO);

    ResponseEntity<String> deleteGuide(String id, String token);
}
