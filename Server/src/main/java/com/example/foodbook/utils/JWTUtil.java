package com.example.foodbook.utils;

import io.jsonwebtoken.*;
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
    //@Value("${jwt.secret.access}")
    //SecretKey
    private String secret= "413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    //@Value("${jwt.secret.refresh}")
    private String refreshSecret="secret2";
    private Duration lifetime=Duration.ofMinutes(60L);
    private Duration lifetime2=Duration.ofMinutes(50L);
    public String generateToken(UserDetails userDetails){ // Можно передавать person
        Map<String,Object> claims = new HashMap<>();
        List<String> rolesList= userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        claims.put("roles",rolesList);
        //Можно добавить любые поля если передать person
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime() +lifetime2.toMillis());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedDate)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
    public String generateRefreshToken(UserDetails userDetails) {
        Date issuedDate = new Date();
        Date expiredDate = new Date(issuedDate.getTime()+ lifetime.toMillis());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
    public String getUsername(String token){
        return getAllClaimsFromToken(token).getSubject();
    }
    public String getEmail(String token){
        return getAllClaimsFromToken(token).get("email",String.class);
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

    private Claims getAllClaimsFromRefreshToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
    public String getUsernameFromRefreshToken(String token){
        return  getAllClaimsFromRefreshToken(token).getSubject();
    }
    public boolean validateAccessToken( String accessToken) {
        return validateToken(accessToken, secret);
    }

    public boolean validateRefreshToken( String refreshToken) {
        return validateToken(refreshToken, secret);
    }

    private boolean validateToken(String token,  String secret) {
        try {
            Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("Token expired" + expEx);
        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt" + unsEx);
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt" + mjEx);
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature" + sEx);
        } catch (Exception e) {
            System.out.println("invalid token" + e);
        }
        return false;
    }


}
