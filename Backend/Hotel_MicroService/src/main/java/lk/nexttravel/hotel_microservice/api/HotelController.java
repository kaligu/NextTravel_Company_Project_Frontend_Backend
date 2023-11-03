/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:22 AM
*/
package lk.nexttravel.hotel_microservice.api;

import lk.nexttravel.hotel_microservice.dto.ReqHotelSaveDTO;
import lk.nexttravel.hotel_microservice.entity.Hotel;
import lk.nexttravel.hotel_microservice.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:22 AM
 */

@RestController
@RequestMapping("/hotel")
@CrossOrigin("*")
public class HotelController {
    @Autowired
    HotelService hotelService;

    //----------Save New Hotel ------
    @PostMapping(value = "/save_new_hotel")
    public ResponseEntity<String> SaveNewGuide(@RequestBody ReqHotelSaveDTO reqNewGuideSaveDTO){
        return hotelService.SaveNewHotel(reqNewGuideSaveDTO);
    }

//    //----------update Guide ------
//    @PutMapping(value = "/save_new_guide")
//    public ResponseEntity<String> updateGuidee(@RequestBody ReqNewGuideSaveDTO reqNewGuideSaveDTO){
//        return guideService.updateGuide(reqNewGuideSaveDTO);
//    }
//
//    //search all guides
//    @GetMapping(value = "/getall-guides")
//    public ResponseEntity<ArrayList<Guide>> getAllGuides(
//            @RequestParam String token
//    ){
//        return guideService.getAllGuides(token);
//    }
//
//    //sdelete guide
//    @DeleteMapping(value = "/delete-guide")
//    public ResponseEntity<String> deleteGuide(
//            @RequestParam String id,
//            @RequestParam String token
//    ){
//        return guideService.deleteGuide(id, token);
//    }

}
