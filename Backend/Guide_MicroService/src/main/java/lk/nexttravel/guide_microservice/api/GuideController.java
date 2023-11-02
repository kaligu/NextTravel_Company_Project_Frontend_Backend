/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/2/2023
  @ Time         : 5:34 AM
*/
package lk.nexttravel.guide_microservice.api;

import lk.nexttravel.guide_microservice.dto.ReqNewGuideSaveDTO;
import lk.nexttravel.guide_microservice.entity.Guide;
import lk.nexttravel.guide_microservice.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/2/2023
 * Time    : 5:34 AM
 */

@RestController
@RequestMapping("/guide")
@CrossOrigin("*")
public class GuideController {
    @Autowired
    GuideService guideService;

    //----------Save New Guide ------
    @PostMapping(value = "/save_new_guide")
    public ResponseEntity<String> SaveNewGuide(@RequestBody ReqNewGuideSaveDTO reqNewGuideSaveDTO){
        return guideService.SaveNewGuide(reqNewGuideSaveDTO);
    }

    //----------update Guide ------
    @PutMapping(value = "/save_new_guide")
    public ResponseEntity<String> updateGuidee(@RequestBody ReqNewGuideSaveDTO reqNewGuideSaveDTO){
        return guideService.updateGuide(reqNewGuideSaveDTO);
    }

    //search all guides
    @GetMapping(value = "/getall-guides")
    public ResponseEntity<ArrayList<Guide>> getAllGuides(
            @RequestParam String token
    ){
        return guideService.getAllGuides(token);
    }
}
