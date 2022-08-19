package com.ramkishore.authorizationmicroservice.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private final CustomAuthenticationFilter customAuthenticationFilter;

	//filtering jwt token from authorization header starting with string bearer

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("START: JWT access token validation");
		String authorizationHeader = request.getHeader(AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String token = authorizationHeader.substring("Bearer ".length());
				String username = customAuthenticationFilter.extractUsername(token);

				UsernamePasswordAuthenticationToken authenticationToken =
						new UsernamePasswordAuthenticationToken(username, null, null);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

				filterChain.doFilter(request, response);
				log.info("END: JWT access token validation");

			} catch (Exception e) {

				log.info("EXCEPTION: Authorization Failed");
				response.setHeader("error", e.getMessage());
				response.setStatus(FORBIDDEN.value());

				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), false);
			}
		} else {
			filterChain.doFilter(request, response);
		}
	}

}
