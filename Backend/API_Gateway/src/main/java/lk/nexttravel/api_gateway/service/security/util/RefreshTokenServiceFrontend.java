/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 11:10 AM
*/
package lk.nexttravel.api_gateway.service.security.util;

import lk.nexttravel.api_gateway.Persistence.UserRepository;
import lk.nexttravel.api_gateway.Persistence.RefreshTokenRepository;
import lk.nexttravel.api_gateway.dto.auth.InternalRefreshTUserDTO;
import lk.nexttravel.api_gateway.entity.User;
import lk.nexttravel.api_gateway.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 11:10 AM
 */

@Service
public class RefreshTokenServiceFrontend {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    InternalRefreshTUserDTO internalRefreshTUserDTO;

    public String createRefreshToken(User user) {
        //check has old saved tokens
        if(refreshTokenRepository.existsById(user.getId())){
            Optional<RefreshToken> refreshToken = refreshTokenRepository.findById(user.getId());
            if(refreshToken.isPresent()){
                //if has existing token delete it
                refreshTokenRepository.delete(refreshToken.get());
            }
        }
        //after deleting ort no create amd add new one
        //create token using UUID
        String token = UUID.randomUUID().toString();

        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(user.getId())
                        .token(token)
                        .expiredate(Instant.now().plusMillis(600000))
                        .build()
        );
        return token;
    }


    public boolean isExpired(RefreshToken token) {
        if (token.getExpiredate().compareTo(Instant.now()) < 0) {
            return true;
        }else {
            return false;
        }
    }


    public InternalRefreshTUserDTO validateUpdateGetUserJWT(String refreshtoken, String username){
        Optional<User> user = userRepository.findUserByName(username);
        Optional<RefreshToken> DBtoken = refreshTokenRepository.findRefreshTokenById(user.get().getId());
        //check this token saved on DB
        if(DBtoken.isPresent()){
            System.out.println("DB "+DBtoken);
            //check DBtoken and recieved token matched
            if(DBtoken.get().getToken().equals(refreshtoken)){
                //check it expired
                if(!isExpired(DBtoken.get())){
                    internalRefreshTUserDTO.setRefreshToken(refreshtoken);
                    internalRefreshTUserDTO.setUserAuthenticated(true);
                    return internalRefreshTUserDTO;
                }else{
                    //if expired
                    //delte it
                    refreshTokenRepository.delete(DBtoken.get());
                    //generate new one
                    if(userRepository.findUserByName(username).isPresent()){
                        String tokenkey = createRefreshToken(userRepository.findUserByName(username).get());
                        internalRefreshTUserDTO.setRefreshToken(tokenkey);
                        internalRefreshTUserDTO.setUserAuthenticated(true);
                        return internalRefreshTUserDTO;
                    }else{
                        //not in db
                        internalRefreshTUserDTO.setUserAuthenticated(false);
                        return internalRefreshTUserDTO;
                    }
                }
            }else{
                internalRefreshTUserDTO.setUserAuthenticated(false);
                return internalRefreshTUserDTO;
            }
        }else{
            internalRefreshTUserDTO.setUserAuthenticated(false);
            return internalRefreshTUserDTO;
        }
    }

}
