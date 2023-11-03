/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 1:21 PM
*/
package lk.nexttravel.vehicle_microservice.repository;

import lk.nexttravel.vehicle_microservice.entity.util.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 1:21 PM
 */

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle , String> {
}
