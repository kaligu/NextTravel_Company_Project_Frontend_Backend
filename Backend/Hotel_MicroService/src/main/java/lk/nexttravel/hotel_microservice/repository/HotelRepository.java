/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 11/3/2023
  @ Time         : 7:33 AM
*/
package lk.nexttravel.hotel_microservice.repository;

import lk.nexttravel.hotel_microservice.entity.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 11/3/2023
 * Time    : 7:33 AM
 */

@Repository
public interface HotelRepository  extends MongoRepository<Hotel, String> {
}
