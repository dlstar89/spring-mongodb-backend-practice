package com.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.restservice.security.JWT.*;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Autowired SecUserDetailsService userDetailsService;
	
	@Autowired MyAuthenticationProvider myAuthenticationProvider;
	@Autowired AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired JWTAuthenticationManager jwtAuthenticationManager;
	@Autowired JWTAuthenticationFilter jwtAuthenticationFilter;

//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder builder) throws Exception {
//		builder
//			.authenticationProvider(myAuthenticationProvider);
//		// .and().inMemoryAuthentication().withUser("user").password("user").roles("USER");
//	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.csrf().disable()
		
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		.and()
		.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/api/healthCheck").permitAll()
        .antMatchers(HttpMethod.POST, "/api/login").permitAll()
        .antMatchers(HttpMethod.GET, "/api/logout").permitAll()
        .antMatchers(HttpMethod.GET, "/api/whoami").permitAll()
        .antMatchers(HttpMethod.GET, "/api/getAllPeople").hasAuthority("ADMIN")
        .anyRequest().authenticated()
        
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	
//				.antMatchers(HttpMethod.GET, "/api/**").access("hasAuthority('ADMIN') or hasAuthority('USER')")
		;
	}
}