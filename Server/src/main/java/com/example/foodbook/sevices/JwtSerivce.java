/*
package com.example.foodbook.sevices;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtSerivce {
    private String jwtSigninKey="sercretkey436984jgmung4938g";
    public String extractUserName(String token){
        return extractClaim(token,Claims::getSubject);
    }
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public boolean isTokenValid(String token,UserDetails userDetails){
        final String userName= extractUserName(token);
        return (userName.equals(userDetails.getUsername()))&&!isTokenExpired(token);
    }
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims =extractAllClaims(token);
                return claimsResolver.apply(claims);
    }
    private String generateToken(Map<String,Object> extraClaims,UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith( SignatureAlgorithm.HS256,getSigningKey()).compact();
    }

    private boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
    }
    private Key getSigningKey(){
        byte[] keyBytes= Decoders.BASE64.decode(jwtSigninKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
*/
