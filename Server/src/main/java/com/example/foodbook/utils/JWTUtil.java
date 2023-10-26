package com.example.foodbook.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JWTUtil {

    private String secret= "secret";


    private Duration lifetime=Duration.ofMinutes(30L);

    public String generateToken(UserDetails userDetails){ // Можно передавать person
        Map<String,Object> claims = new HashMap<>();
        List<String> rolesList= userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles",rolesList);
        //Можно добавить любые поля если передать person
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime()+ 1000 * 60 * 24);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,"413F4428472B4B6250655368566D5970337336763979244226452948404D6351")
                .compact();
    }
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode("413F4428472B4B6250655368566D5970337336763979244226452948404D6351");
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsername(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public List<String> getRoles(String token){
        return getAllClaimsFromToken(token).get("roles",List.class);
    }
    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

}
