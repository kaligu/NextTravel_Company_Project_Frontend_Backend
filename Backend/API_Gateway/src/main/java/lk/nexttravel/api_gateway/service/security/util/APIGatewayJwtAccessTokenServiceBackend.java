/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/22/2023
  @ Time         : 1:36 AM
*/
package lk.nexttravel.api_gateway.service.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.nexttravel.api_gateway.advice.util.InternalServerException;
import lk.nexttravel.api_gateway.dto.auth.InternalJWTUserDTO;
import lk.nexttravel.api_gateway.entity.AuthUser;
import lk.nexttravel.api_gateway.util.RoleTypes;
import lk.nexttravel.api_gateway.util.security.SecurityCodes;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.function.Function;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/22/2023
 * Time    : 1:36 AM
 */

@Component
public class APIGatewayJwtAccessTokenServiceBackend {
    public final long JWT_TOKEN_VALIDITY = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_KEY_VALIDITY;

    public final String JWT_TOKEN_KEY = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_KEY;

    public final String JWT_TOKEN_USERNAME = SecurityCodes.BACKEND_APIGATEWAY_JWT_TOKEN_USERNAME;
//
//    //********---------------------------------------------------------------------generate token
//    public String generateToken() {
//        Map<String, Object> claims = new HashMap<>();
//        String key = doGenerateToken(claims, JWT_TOKEN_USERNAME);
//        System.out.println("Gateway created: "+key);
//        System.out.println("Gateway check: "+validateJWTToken(key));
//        return key;
//    }
//
//    //***********-------------------------------------------------------validateUpdateGetUserJWT  - this method check JWT and if it expired create new it return
//    public boolean validateJWTToken(String token){
//        //check JWT
//        try {
//            Jwts.parser()
//                    .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
//                    .parseClaimsJws(token);
//            System.out.println("Gateway vauthenticated done"+token);
//            return true;
//
//        } catch (Exception e) { //if token expired
//            System.out.println("Gateway vauthenticated Error"+token);
//            return false;
//
//        }
//    }
//
//    //validate token
//    public void validateToken(String token) {
//        try {
//            Jwts.parser()
//                    .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
//                    .parseClaimsJws(token);
//        } catch (ExpiredJwtException e) {
//            System.out.println("Token expired"+e);
//        } catch (Exception e) {
//            System.out.println("Token not validated"+e);
//        }
//
//    }
//
//    //retrieve username from jwt token
//    public String getUsernameFromToken(String token) {
//        return getClaimFromToken(token, Claims::getSubject);
//    }
//
//    //retrieve expiration date from jwt token
//    public Date getExpirationDateFromToken(String token) {
//        return getClaimFromToken(token, Claims::getExpiration);
//    }
//
//    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
//        final Claims claims = getAllClaimsFromToken(token);
//        return claimsResolver.apply(claims);
//    }
//
//    //for retrieveing any information from token we will need the secret key
//    private Claims getAllClaimsFromToken(String token) {
//        return Jwts.parser().setSigningKey(JWT_TOKEN_KEY).parseClaimsJws(token).getBody();
//    }
//
//    //check if the token has expired
//    private Boolean isTokenExpired(String token) {
//        final Date expiration = getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//
//    //while creating the token -
//    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
//    //2. Sign the JWT using the HS512 algorithm and secret key.
//    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
//    //   compaction of the JWT to a URL-safe string
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
//                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_KEY).compact();
//    }

    /////////
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken() {
        return generateToken(new HashMap<>(), JWT_TOKEN_USERNAME);
    }

    public boolean isTokenValid(String token) {
        final String userName = extractUserName(token);
        return (userName.equals(JWT_TOKEN_USERNAME)) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, String JWT_TOKEN_USERNAME) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(JWT_TOKEN_USERNAME)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY)).signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
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
