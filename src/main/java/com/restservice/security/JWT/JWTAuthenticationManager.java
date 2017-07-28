package com.restservice.security.JWT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.restservice.security.User;
import com.restservice.security.UserRepository;

@Component
public class JWTAuthenticationManager implements AuthenticationManager{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("AUTH MANAGER: " + authentication);
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
		
        System.out.println("userRepository: " + userRepository);
        User user = userRepository.findByUsername(username);
        System.out.println("user: " + user);
        
        if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
            throw new BadCredentialsException("Username not found.");
        }
        
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Wrong password.");
        }
        
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

}
