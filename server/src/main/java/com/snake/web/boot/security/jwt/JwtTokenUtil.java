package com.snake.web.boot.security.jwt;

import com.snake.web.boot.module.system.model.User;
import com.snake.web.boot.utils.HashMaps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by HP on 2018/10/25.
 */
@Component
public class JwtTokenUtil implements Serializable {

    private final static String key_created = "created";
    private final static String key_subject = "sub";
    private final String secret = "security";

    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 5);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateToken(String username) {
        Map<String, Object> claims = HashMaps.asHashMap(new String[]{key_subject, key_created}, new Object[]{username, new Date()});
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (null != claims) {
                return claims.getSubject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (null != claims) {
                Date expiration = claims.getExpiration();
                if(null != expiration) {
                    return expiration.before(new Date());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public String refreshToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (null != claims) {
                claims.put(key_created, new Date());
                return generateToken(claims);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        if(null != userDetails) {
            User user = (User) userDetails;
            String username = getUsernameFromToken(token);
            return username.equals(user.getUsername()) && (isTokenExpired(token));
        }
        return false;
    }

}
