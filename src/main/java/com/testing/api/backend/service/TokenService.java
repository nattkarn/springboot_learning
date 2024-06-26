package com.testing.api.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.testing.api.backend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${app.token.secret}")
    private String secret = "Arm@1144nb";

    @Value("${app.token.issuer}")
    private String issuer = "BackendService";


    private Algorithm algorithm(){
        return Algorithm.HMAC256(secret);
    }
    public String tokenize(User user) {


        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        Date expiresAt = calendar.getTime();



        return JWT.create()
                .withIssuer(issuer)
                .withClaim("principal", user.getId())
                .withClaim("role", "USER")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();
            return verifier.verify(token);

        }catch (Exception e){
            return null;
        }

    }
}
