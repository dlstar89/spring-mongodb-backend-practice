package com.restservice.security.JWT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restservice.security.User;
import com.restservice.security.UserRepository;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	UserRepository userRepo;
	
	public JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		
		JWTAccountCredentials creds = new ObjectMapper().readValue(req.getInputStream(), JWTAccountCredentials.class);
		
		Authentication auth = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(),creds.getPassword(), Collections.emptyList())); 
		
		System.out.println("ATTEMPT AUTH: " + auth);
		
		return auth;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		try {
			TokenAuthenticationService.addAuthentication(res, auth.getName());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}