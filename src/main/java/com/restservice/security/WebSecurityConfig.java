package com.restservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
	@Autowired JWTAuthenticationEntryPoint authenticationEntryPoint;
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
			.cors()
			
			.and()
				.csrf().disable()
			
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			.and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
			
			.and()
			.authorizeRequests()
	        .antMatchers("/").permitAll()
	        .antMatchers("/api/healthCheck").permitAll()
	        
	        .antMatchers(HttpMethod.POST, "/api/login").permitAll()
	        .antMatchers(HttpMethod.POST, "/api/logout").permitAll()
	        .antMatchers(HttpMethod.GET, "/api/whoami").permitAll()
//        	.antMatchers(HttpMethod.GET, "/api/getAllPeople").hasAuthority("ADMIN")
//	        .antMatchers(HttpMethod.GET, "/api/**").access("hasAuthority('ADMIN') or hasAuthority('USER')")
	        .anyRequest().authenticated()
	        
	        .and()
		        // We filter the api/login requests
		        .addFilterBefore(new JWTLoginFilter("/api/login", jwtAuthenticationManager), UsernamePasswordAuthenticationFilter.class)
		        // And filter other requests to check the presence of JWT in header
		        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
		;
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**","/swagger-resources/configuration/ui","/swagge‌​r-ui.html");
    }
}