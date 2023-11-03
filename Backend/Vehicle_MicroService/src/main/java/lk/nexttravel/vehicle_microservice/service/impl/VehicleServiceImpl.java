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
                                .vehicle_id(id)
                                .vehicle_type(reqVehicleSaveDTO.getVehicle_type())
                                .vehicle_fuel_type(reqVehicleSaveDTO.getVehicle_fuel_type())
                                .vehicle_hybrid_or_non_hybrid(reqVehicleSaveDTO.getVehicle_hybrid_or_non_hybrid())
                                .vehicle_seat_capacity(reqVehicleSaveDTO.getVehicle_seat_capacity())
                                .vehicle_transmission_type(reqVehicleSaveDTO.getVehicle_transmission_type())
                                .vehicle_fuel_usage(reqVehicleSaveDTO.getVehicle_fuel_usage())
                                .vehicle_perday_vehicle_fee(reqVehicleSaveDTO.getVehicle_perday_vehicle_fee())
                                .vehicle_category(reqVehicleSaveDTO.getVehicle_category())

                                .vehicle_image_sideview(reqVehicleSaveDTO.getVehicle_image_sideview())
                                .vehicle_image_frontview(reqVehicleSaveDTO.getVehicle_image_frontview())
                                .vehicle_image_rearview(reqVehicleSaveDTO.getVehicle_image_rearview())
                                .vehicle_image_front_interior_view(reqVehicleSaveDTO.getVehicle_image_front_interior_view())
                                .vehicle_image_rear_interior_view(reqVehicleSaveDTO.getVehicle_image_rear_interior_view())

                                .vehicle_driver_name(reqVehicleSaveDTO.getVehicle_driver_name())
                                .vehicle_driver_tell(reqVehicleSaveDTO.getVehicle_driver_tell())
                                .vehicle_driver_license_rear_view(reqVehicleSaveDTO.getVehicle_driver_license_rear_view())
                                .vehicle_driver_license_front_view(reqVehicleSaveDTO.getVehicle_driver_license_front_view())
                                .vehicle_driver_remarks(reqVehicleSaveDTO.getVehicle_driver_remarks())

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
