/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:22 AM
*/
package lk.nexttravel.user_microservice.persistence;

import lk.nexttravel.user_microservice.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:22 AM
 */

@Repository
public interface AdminRepository extends MongoRepository<Admin, Long> {
}
