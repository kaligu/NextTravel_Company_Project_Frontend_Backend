/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 1:24 PM
*/
package lk.nexttravel.vehicle_microservice.api;

import lk.nexttravel.vehicle_microservice.dto.ReqVehicleSaveDTO;
import lk.nexttravel.vehicle_microservice.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 1:24 PM
 */

@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    //----------Save New Hotel ------
    @PostMapping(value = "/save_new_vehicle")
    public ResponseEntity<String> SaveNewGuide(@RequestBody ReqVehicleSaveDTO reqVehicleSaveDTO{
        return vehicleService.SaveNewVehicel(reqVehicleSaveDTO);
    }
}
