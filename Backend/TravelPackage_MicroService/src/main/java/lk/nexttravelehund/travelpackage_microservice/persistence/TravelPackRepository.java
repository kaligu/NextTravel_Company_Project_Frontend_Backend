/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 6:51 PM
*/
package lk.nexttravelehund.travelpackage_microservice.persistence;

import lk.nexttravelehund.travelpackage_microservice.entity.TravelPackage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 6:51 PM
 */
public interface TravelPackRepository  extends MongoRepository<TravelPackage, String> {
}
