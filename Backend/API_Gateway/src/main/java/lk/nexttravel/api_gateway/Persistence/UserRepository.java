/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 11:02 AM
*/
package lk.nexttravel.api_gateway.Persistence;

import lk.nexttravel.api_gateway.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 11:02 AM
 */

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByName(String name);
    boolean existsByName(String username);
    boolean deleteByName(String name);
}
