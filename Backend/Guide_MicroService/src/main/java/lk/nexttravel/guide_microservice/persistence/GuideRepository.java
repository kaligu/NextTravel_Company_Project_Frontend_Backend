/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 9:02 PM
*/
package lk.nexttravel.guide_microservice.persistence;

import lk.nexttravel.guide_microservice.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 9:02 PM
 */

@Repository
public interface GuideRepository extends MongoRepository<Guide , String> {
}
