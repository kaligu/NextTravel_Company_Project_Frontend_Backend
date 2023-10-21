/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:22 AM
*/
package lk.nexttravel.user_microservice.persistence;

import lk.nexttravel.user_microservice.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:22 AM
 */

@Repository
public interface ClientRepository extends MongoRepository<Client, Long> {
    Optional<Client> findClientById(String id);
}
