package com.ramkishore.authorizationmicroservice.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class CustomAuthenticationFilter {

	public String generateToken(String username){
		Algorithm algorithm = Algorithm.HMAC256("Highly-Secured-key".getBytes());

		log.info("START: JWT access token generation.");
		String access_token = JWT.create()
				.withSubject(username)
				.withExpiresAt(new Date(System.currentTimeMillis()+10*60*1000))
				.withClaim("role","admin-user")
				.sign(algorithm);
		log.info("END: JWT access token generation.");
		return access_token;
	}
	public String extractUsername(String token) {
		Algorithm algorithm = Algorithm.HMAC256("Highly-Secured-key".getBytes());
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT decodedJWT = verifier.verify(token);
		return decodedJWT.getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		log.info("START: validateToken");
		final String username = extractUsername(token);
		log.info("END: validateToken");
		return (username.equals(userDetails.getUsername()));
	}

}
