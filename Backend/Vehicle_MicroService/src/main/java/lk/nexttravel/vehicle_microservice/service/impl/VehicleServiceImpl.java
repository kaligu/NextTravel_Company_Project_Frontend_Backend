/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 1:23 PM
*/
package lk.nexttravel.vehicle_microservice.service.impl;

import lk.nexttravel.vehicle_microservice.dto.ReqVehicleSaveDTO;
import lk.nexttravel.vehicle_microservice.entity.util.Vehicle;
import lk.nexttravel.vehicle_microservice.repository.VehicleRepository;
import lk.nexttravel.vehicle_microservice.service.SequenceGeneratorService;
import lk.nexttravel.vehicle_microservice.service.VehicleService;
import lk.nexttravel.vehicle_microservice.service.security.APIGatewayJwtAccessTokenServiceBackend;
import lk.nexttravel.vehicle_microservice.util.RespondCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 1:23 PM
 */

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private APIGatewayJwtAccessTokenServiceBackend apiGatewayJwtAccessTokenServiceBackend;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public ResponseEntity<String> SaveNewVehicel(ReqVehicleSaveDTO reqVehicleSaveDTO) {
        //check authentication
//        System.out.println(reqHotelSaveDTO.toString());
        String id = "V00"+sequenceGeneratorService.generateSequence(Vehicle.SEQUENCE_NAME);
        try {
            if (apiGatewayJwtAccessTokenServiceBackend.isTokenValid(reqVehicleSaveDTO.getToken())) {  //check gateway token
                //save into database
                vehicleRepository.save(
                        Vehicle.builder()
                                .
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
