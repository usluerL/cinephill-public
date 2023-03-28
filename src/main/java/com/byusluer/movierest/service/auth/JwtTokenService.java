package com.byusluer.movierest.service.auth;


import com.byusluer.movierest.constants.AppConstants;
import com.byusluer.movierest.exception.MovieApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenService {

    @Value("${app.jwtSecretKey}")
    private String jwtSecret;

    @Value("${app.expiration}")
    private long jwtExpirationDate;

    // generate JWT token
    public String generateToken(Authentication authentication){
        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(getKey())
                .compact();
        return token;
    }

    private Key getKey(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }


    // get username from Jwt token
    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        return username;
    }


    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AppConstants.AUTH_KEY_NAME);
        if (StringUtils.hasText(bearer) && bearer.startsWith(AppConstants.JWT_TOKEN_NAME)) {
            return bearer.substring(AppConstants.JWT_TOKEN_NAME.length());
        }
        return null;
    }

    // validate Jwt token
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException ex) {
            throw new MovieApiException("Invalid Token", HttpStatus.BAD_REQUEST);
        } catch (ExpiredJwtException ex) {
            throw new MovieApiException("Expired JWT token", HttpStatus.BAD_REQUEST);
        } catch (UnsupportedJwtException ex) {
            throw new MovieApiException( "Unsupported JWT token", HttpStatus.BAD_REQUEST);
        } catch (IllegalArgumentException ex) {
            throw new MovieApiException("JWT claims string is empty.", HttpStatus.BAD_REQUEST);
        }
    }
}
