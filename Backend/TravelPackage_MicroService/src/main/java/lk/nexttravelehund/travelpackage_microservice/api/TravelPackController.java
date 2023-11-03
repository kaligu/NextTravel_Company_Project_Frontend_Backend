/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 6:40 PM
*/
package lk.nexttravelehund.travelpackage_microservice.api;

import lk.nexttravelehund.travelpackage_microservice.dto.ReqNewTravelPackageDTO;
import lk.nexttravelehund.travelpackage_microservice.service.TravelPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 6:40 PM
 */

@RestController
@RequestMapping("/travelpack")
@CrossOrigin("*")
public class TravelPackController {
    @Autowired
    TravelPackService travelPackService;

    //----------Save New Hotel ------
    @PostMapping(value = "/save_new_travelpack")
    public ResponseEntity<String> SaveNewTravelPack(@RequestBody ReqNewTravelPackageDTO reqNewGuideSaveDTO){
        return travelPackService.saveNewTravelPack(reqNewGuideSaveDTO);
    }

}
