/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/21/2023
  @ Time         : 10:39 AM
*/
package lk.nexttravel.api_gateway.service.security.util;

import lk.nexttravel.api_gateway.dto.auth.InternalJWTUserDTO;
import lk.nexttravel.api_gateway.service.AuthService;
import lk.nexttravel.api_gateway.util.security.SecurityCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author : H.C.Kaligu Jayanath
 * Date    : 10/21/2023
 * Time    : 10:39 AM
 */

@Component
public class JwtAccessTokenService {
    @Autowired
    AuthService authService;

    public final long JWT_TOKEN_VALIDITY = SecurityCodes.APIGATEWAY_JWT_TOKEN_KEY_VALIDITY;

    public final String JWT_TOKEN_KEY = SecurityCodes.APIGATEWAY_JWT_TOKEN_KEY;

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(JWT_TOKEN_KEY).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(String Username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, Username);
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_TOKEN_KEY).compact();
    }

    //validate token
    public void validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
                    .parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired"+e);
        } catch (Exception e) {
            System.out.println("Token not validated"+e);
        }

    }

    //validateUpdateGetUserJWT  - this method check JWT and if it expired create new and role and user name return
    public InternalJWTUserDTO validateUpdateGetUserJWT(String token, String username){
        InternalJWTUserDTO internalJWTUserDTO = new InternalJWTUserDTO();
        //check JWT
        try {
            if(authService.getRoleByUsername(username).isPresent()){ //check database and get role
                internalJWTUserDTO.setRole(authService.getRoleByUsername(username).get());//gett role from user database

                Jwts.parser()
                        .setSigningKey( new SecretKeySpec(Base64.getDecoder().decode(JWT_TOKEN_KEY), SignatureAlgorithm.HS512.getJcaName()))
                        .parseClaimsJws(token);

                //add data
                internalJWTUserDTO.setAccessToken(token);
                internalJWTUserDTO.setUsername(username);
                internalJWTUserDTO.setUserAuthorized(true);
                return internalJWTUserDTO;

            }else{
                internalJWTUserDTO.setUserAuthorized(false);
                return internalJWTUserDTO;
            }

        } catch (ExpiredJwtException e) { //if token expired
            internalJWTUserDTO.setUserAuthorized(true);
            internalJWTUserDTO.setAccessToken(generateToken(username)); //generate and add new token
            internalJWTUserDTO.setUsername(username);
            return internalJWTUserDTO;

        } catch (Exception e) { //if token mismatched
            internalJWTUserDTO.setUserAuthorized(false);
            internalJWTUserDTO.setRole(null);
            return internalJWTUserDTO;
        }

    }
}
