/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/31/2023
  @ Time         : 9:02 PM
*/
package lk.nexttravel.guide_microservice.persistence;

import lk.nexttravel.guide_microservice.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/31/2023
 * Time    : 9:02 PM
 */

@Repository
public interface GuideRepository extends MongoRepository<Guide , String> {
    ArrayList<Guide> findAll();
}
