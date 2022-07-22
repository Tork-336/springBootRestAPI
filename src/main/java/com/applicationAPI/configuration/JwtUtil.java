package com.applicationAPI.configuration;

import com.applicationAPI.controller.Form.LoginForm;
import com.applicationAPI.execption.BusinessLogicException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private static final String SECRETKEYJWT = "256H2";
    private static ObjectMapper mapper = new ObjectMapper();

    public static String generateToken(LoginForm userSession) throws JsonProcessingException {
        return Jwts.builder().setIssuedAt(new Date())
                .setSubject(mapper.writeValueAsString(userSession))
                .signWith(SignatureAlgorithm.HS256, SECRETKEYJWT.getBytes(StandardCharsets.UTF_8)).compact();
    }

    public static LoginForm getToken(String token) {
        LoginForm userSesion = new LoginForm();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRETKEYJWT.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(token)
                    .getBody();
            if (!claims.isEmpty()) {
                userSesion = mapper.readValue(claims.get("sub").toString(), LoginForm.class);
            }
        } catch (Exception exception) {
            throw new BusinessLogicException(" Error con el token.");
        }
        return userSesion;
    }
}
