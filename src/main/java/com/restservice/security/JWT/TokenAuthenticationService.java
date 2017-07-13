package com.restservice.security.JWT;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


class TokenAuthenticationService {
	static final long EXPIRATIONTIME = 864_000_000; // 10 days
	static final String SECRET = "ThisIsASecret";
	static final String TOKEN_PREFIX = "Bearer";
	static final String HEADER_STRING = "Authorization";

	static void addAuthentication(HttpServletResponse res, String id) {
		String JWT = Jwts.builder()
				.setSubject(id)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		System.out.println(id);
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
	}

	static String getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_STRING);
		if (token != null) {
			// parse the token.
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();

			
//			String[] s = user.split(":");
//			System.out.println("TOKEN: " + s[0] +" _ "  + s[1].substring(1, s[1].length()-1));
			
//			List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(s[1].substring(1, s[1].length()-1));
//			System.out.println("grantedAuthorities: " + grantedAuthorities);
			 
//			return user != null ? new UsernamePasswordAuthenticationToken(s[0], null, grantedAuthorities) : null;
			
			return user;
		}
		return null;
	}
}