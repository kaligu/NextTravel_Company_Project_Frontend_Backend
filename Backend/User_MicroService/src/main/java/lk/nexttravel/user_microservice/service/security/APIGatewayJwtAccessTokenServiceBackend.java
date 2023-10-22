package lk.nexttravel.user_microservice.service.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.nexttravel.user_microservice.advice.util.InternalServerException;
import lk.nexttravel.user_microservice.dto.security.InternalTokenDTO;
import lk.nexttravel.user_microservice.util.security.SecurityCodes;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/22/2023
 * Time    : 1:36 AM
 */

@Component
public class APIGatewayJwtAccessTokenServiceBackend {

    public final String JWT_TOKEN_KEY = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_KEY;

    public final String JWT_TOKEN_USERNAME = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_USERNAME;

//    //***********-------------------------------------------------------validateUpdateGetUserJWT  - this method check JWT and if it expired create new it return
//    public InternalTokenDTO validateJWTToken(String token){
//        System.out.println("Microservice "+token);
//        InternalTokenDTO internalTokenDTO = new InternalTokenDTO();
//        //check JWT
//        try {
//
//            Jwts.parser()
//                    .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
//                    .parseClaimsJws(token);
//
//            //add data to DTO
//            internalTokenDTO.setToken(token);
//            internalTokenDTO.setAuthenticated(true);
//
//            System.out.println("Microservice vauthenticated done");
//
//
//            return internalTokenDTO;
//
//        } catch (Exception e) { //if token expired
//            //add data to DTO
//            internalTokenDTO.setToken(token);
//            internalTokenDTO.setAuthenticated(false);
//
//            System.out.println("Microservice vauthenticated Error"+e.getMessage());
//
//            return internalTokenDTO;
//        }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    public boolean isTokenValid(String token) {
        final String userName = extractUserName(token);
        return (userName.equals(JWT_TOKEN_USERNAME)) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }



    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_TOKEN_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }



}
