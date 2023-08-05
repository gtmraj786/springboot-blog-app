package com.gtmraj.blog.security;

import com.gtmraj.blog.exception.BlogAPIException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private Long jwtExpirationDate;


    // generate JWT token
    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        System.out.println("OOOOOO"+username);
        Date currentDate = new Date();
        System.out.println(currentDate);
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        System.out.println(expireDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        System.out.println(token);
        return  token;
    }

    private Key key()
    {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // get username from Jwt token
    public String getUsername(String token){

       Claims claims = Jwts.parserBuilder()
                  .setSigningKey(key())
                  .build()
                  .parseClaimsJws(token)
                  .getBody();
       String username = claims.getSubject();
       return  username;
    }

    // validate Jwt token
    public boolean validateToken(String token){

         try{
             Jwts.parserBuilder()
                     .setSigningKey(key())
                     .build()
                     .parse(token);
             return true;

         }catch (MalformedJwtException exception){
         throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Invalid Jwt token");
         }catch (ExpiredJwtException exception){
             throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Expired Jwt token");
         }catch (UnsupportedJwtException exception){
             throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Unsupported Jwt token");
         }catch (IllegalArgumentException exception){
             throw new BlogAPIException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
         }


    }


}
