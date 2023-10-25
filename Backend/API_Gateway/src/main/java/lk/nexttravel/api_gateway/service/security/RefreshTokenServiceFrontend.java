/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 11:10 AM
*/
package lk.nexttravel.api_gateway.service.security;

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


    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }


    public boolean isExpired(RefreshToken token) {
        if (token.getExpiredate().compareTo(Instant.now()) < 0) {
            return true;
        }else {
            return false;
        }
    }


    public InternalRefreshTUserDTO validateUpdateGetUserJWT(String refreshtoken, String username){
        InternalRefreshTUserDTO internalRefreshTUserDTO = new InternalRefreshTUserDTO();
        //check this token saved on DB
        if(findByToken(refreshtoken).isPresent()){
            RefreshToken Token = findByToken(refreshtoken).get();
            //check it expired
            if(!isExpired(Token)){
                internalRefreshTUserDTO.setRefreshToken(refreshtoken);
                internalRefreshTUserDTO.setUserAuthenticated(true);
                return internalRefreshTUserDTO;
            }else{
                //if expired
                //delte it
                refreshTokenRepository.delete(Token);
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
    }

}
