package com.piseth.java.school.phoneshopenight.config.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.piseth.java.school.phoneshopenight.exception.ApiException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenVerifyFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// check the header have Authorization
		
		String authorizationHeader = request.getHeader("Authorization");
		
		// check condition if don't have finish this request 
		
		if(Objects.isNull(authorizationHeader) || !authorizationHeader.startsWith("Bearer ")) {
			
			filterChain.doFilter(request, response);
			return;
			// finished can not login if does not have "Authorization" in the header
		}
		// else = have Auhorization we get only token and remove "Bearer "
		String token = authorizationHeader.replace("Bearer ", "");
		String secretKey ="abcddfdsf1243abcddfdsf1243abcddfdsf1243";
		
		try {
			
			Jws<Claims> claimsJws = Jwts.parser()
					.setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes())).build().parseClaimsJws(token);
					
					Claims body = claimsJws.getBody();
				
					// now we get the Authorization 
					
					String username = body.getSubject();
					
					// we want object inside authorities ==> we put the Key then it will return object [ROLE_ADMIN : brand:read...]
					
					List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
					
					// 3: get Get Value from Map Object  
				
					Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
							.map(x -> new SimpleGrantedAuthority(x.get("authority")))
							.collect(Collectors.toSet());	
					
					//==>  "authority":  ==> "ROLE_SALE"
					
					// 2 : Need Authentication 
					
					Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
					
					// 1: to make it remember we use Security Holder
					
					SecurityContextHolder.getContext().setAuthentication(authentication);	
					filterChain.doFilter(request, response); // doFilter in order tell the filete finish step 
			
		} catch (Exception e) {
			log.info(e.getMessage());
			throw new ApiException(HttpStatus.BAD_REQUEST, e.getMessage());
		}		
		
		
		
		
	}

}
