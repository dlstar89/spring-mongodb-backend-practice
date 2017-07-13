package com.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider{
		
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("AUTH: " + authentication);
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
        		
//		return null;
        return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
