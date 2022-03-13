package com.bilgeadam.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class JwtTokenManager {
    public Optional<String> createToken (String profileId) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123");

            token = JWT.create()
                    .withAudience()
                    .withClaim("profileId", profileId)
                    .withIssuer("bilgeadam.com")
                    .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                    .withIssuedAt(new Date())
                    .sign(algorithm);
            return Optional.of(token);
        } catch (Exception e){
            return Optional.empty();

        }
    }

    public boolean validateTokenOne(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123");
            JWT.require(algorithm)
                    .withIssuer("bilgeadam.com")
                    .build()
                    .verify(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123");

            JWTVerifier jwtVerifier =  JWT.require(algorithm)
                    .withIssuer("bilgeadam.com")
                    .build();
            /**
             * Incoming token will be decoded
             */
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode==null) return false;
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Optional<String> getProfileId(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256("123");
            JWTVerifier jwtVerifier =  JWT.require(algorithm)
                    .withIssuer("bilgeadam.com")
                    .build();
            DecodedJWT decode = jwtVerifier.verify(token);
            if (decode==null) return Optional.empty();
            String profileId = decode.getClaim("profileId").asString();
            return Optional.of(profileId);
        }catch (Exception e){
            return Optional.empty();
        }
    }
}
