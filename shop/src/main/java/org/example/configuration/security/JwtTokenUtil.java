package org.example.configuration.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.example.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    private final String jwtSecret = "zdtlDeos873KjjuwodkLDPDplk3JKyy*did5sseII6m6wTTgsNFhqzjqP";  //ключ, яким ми шифруємо (будь-які букви чи цифри)
    private final String jwtIssuer = "step.io";   //вказує хто власник цього токена. Можна вписати ім'я свого домена

    //метод призначений для того, щоб для визначеного юзера зробити jwt token
    public String generateAccessToken(UserEntity user) {

        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getEmail()))
                .claim("email", user.getEmail())
                .claim("image", user.getImage())
                //.claim("roles", user.getUsername())
                .setIssuer(jwtIssuer) //записуємо хто власник токена
                .setIssuedAt(new Date())  //коли був створений токен
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week  зазначаємо скільки часу буде жити токен
                .signWith(SignatureAlgorithm.HS512, jwtSecret)  //шифруємо токен за допомогою сікретного ключа
                .compact();
    }

    // з токена можна витягнути Id юзера
    public String getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)    // перевіряється чи цей токен видавався нашим серваком
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[0]; //з токена бере перший елемент Id
    }
    // з токена можна витягнути username юзера
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }
// метод повертає дату до якої живе токен
    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }
//перевфряє чи наш токен валідний і чи видавався нашим сервером
    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT signature - "+ ex.getMessage());
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT token - " + ex.getMessage());
        } catch (ExpiredJwtException ex) {
            System.out.println("Expired JWT token - " + ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token - " + ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty - " + ex.getMessage());
        }
        return false;
    }
}