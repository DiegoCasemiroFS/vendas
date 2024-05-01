package com.github.DiegoCasemiroFS.vendas.security;

import com.github.DiegoCasemiroFS.vendas.VendasApplication;
import com.github.DiegoCasemiroFS.vendas.entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Value("${jwt.key}")
    private String jwtKey;

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService jwtService = contexto.getBean(JwtService.class);

        Usuario usuario = Usuario.builder().login("fulano").build();

        String token = jwtService.gerarToken(usuario);
        System.out.println(token);

        boolean tokenValido = jwtService.tokenValido(token);
        System.out.println("O token está válido? " + tokenValido);
    }

    public String gerarToken(Usuario usuario) {
        long minutesExpire = Long.valueOf(this.jwtExpiration);
        LocalDateTime plusMinutes = LocalDateTime.now().plusMinutes(minutesExpire);
        Instant toInstant = plusMinutes.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(toInstant);

        return Jwts.builder()
                .setSubject(usuario.getLogin())
                .setExpiration(from)
                .signWith(SignatureAlgorithm.HS512, jwtKey)
                .compact();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);
            Date expiration = claims.getExpiration();
            LocalDateTime localDateTime = expiration.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(jwtKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }
}
