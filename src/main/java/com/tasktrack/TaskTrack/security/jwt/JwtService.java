package com.tasktrack.TaskTrack.security.jwt;

import com.tasktrack.TaskTrack.security.JwtAuthDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtService {

    public static final Logger log = LogManager.getLogger(JwtService.class);

    @Value("7c403cacb68da6c7c80da55a77269ad58f892875a0280c4c1fd719ff73434247")
    private String jwtSecret;

    public JwtAuthDto generateAuthToken(String username) {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setAccessToken(generateJwtToken(username));
        jwtAuthDto.setRefreshToken(generateRefreshToken(username));
        return jwtAuthDto;
    }

    public JwtAuthDto refreshBaseToken(String username, String refreshToken) {
        JwtAuthDto jwtAuthDto = new JwtAuthDto();
        jwtAuthDto.setAccessToken(generateJwtToken(username));
        jwtAuthDto.setRefreshToken(refreshToken);
        return jwtAuthDto;
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (ExpiredJwtException exception) {
            log.error("Expired JwtException", exception);
        } catch (UnsupportedJwtException exception) {
            log.error("Unsupported JwtException", exception);
        } catch (MalformedJwtException exception) {
            log.error("Malformed JwtException", exception);
        } catch (SecurityException exception) {
            log.error("Security Exception", exception);
        } catch (Exception exception) {
            log.error("Invalid token", exception);
        }
        return false;
    }

    private String generateJwtToken(String username) {
        Date date = Date.from(LocalDateTime.now().plusMinutes(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(username)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private String generateRefreshToken(String username) {
        Date date = Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .subject(username)
                .expiration(date)
                .signWith(getSignInKey())
                .compact();
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
