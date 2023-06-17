package com.luiz.projetos.check.security;

import com.luiz.projetos.check.model.Usuario;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {
    @Value("${check.jwt.expiration}")
    private String jwtExpirationMin;
    @Value("${check.jwt.access-key}")
    private String jwtSecret;
    private static final Logger logger = LoggerFactory.getLogger(JwtService.class);

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String gerarToken(Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Long exp = Long.valueOf(jwtExpirationMin);
        Instant expInst = LocalDateTime.now().plusMinutes(exp).atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(expInst);

        return Jwts.builder()
                .setExpiration(data)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setSubject(userDetails.getUsername())
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims obterClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(key())
                .build().parseClaimsJws(token).getBody();
    }

    public boolean isTokenValid(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(token);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }

    public String getLoginUsuario(String token) throws ExpiredJwtException {
        return obterClaims(token).getSubject();
    }

}
