package com.ramkishore.authorizationmicroservice.controller;


import com.ramkishore.authorizationmicroservice.exception.ResourceNotFound;
import com.ramkishore.authorizationmicroservice.model.AuthRequest;
import com.ramkishore.authorizationmicroservice.filter.CustomAuthenticationFilter;
import com.ramkishore.authorizationmicroservice.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthorizationController {

	private final CustomAuthenticationFilter customAuthenticationFilter;

	private final UserDetailService userDetailService;

	private final AuthenticationManager authenticationManager;


	//JWT token authentication with Username and password.
	
	@PostMapping("/authenticate")
	public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {

		log.info("START: generateToken");
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		} catch (Exception e) {
			log.error("EXCEPTION: generateToken");
			throw new ResourceNotFound("user not found");
		}

		log.info("END: generateToken");
		return ResponseEntity.ok(customAuthenticationFilter.generateToken(authRequest.getUsername()));
	}
	
	
	//Validating of the generated JWT token to access '/authorize' endpoint
	@GetMapping("/authorize")
	public ResponseEntity<Boolean> authorization(@RequestHeader("Authorization") String token1) {

		log.info("START: authorization");
		String token = token1.substring("Bearer ".length());

		UserDetails user = userDetailService.loadUserByUsername(customAuthenticationFilter.extractUsername(token));

		if (customAuthenticationFilter.validateToken(token, user)) {
			log.info("END: authorization Success");
			return new ResponseEntity<>(true, HttpStatus.OK);

		} else {
			log.info("END: authorization Fail");
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}

	}

}
