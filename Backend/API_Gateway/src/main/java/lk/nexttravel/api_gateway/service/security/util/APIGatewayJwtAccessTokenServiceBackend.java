/*
  @ Author       : C.Kaligu Jayanath
  @ Prjoect Name : NextTravel_Company_Project_Frontend_Backend
  @ Date         : 10/22/2023
  @ Time         : 1:36 AM
*/
package lk.nexttravel.api_gateway.service.security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.nexttravel.api_gateway.util.security.SecurityCodes;
import org.springframework.stereotype.Component;

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

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String generateToken() {
        return generateToken(new HashMap<>());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims) {
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
