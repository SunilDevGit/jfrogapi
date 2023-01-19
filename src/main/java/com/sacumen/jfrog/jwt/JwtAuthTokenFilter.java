package com.sacumen.jfrog.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sacumen.jfrog.commons.AppConstants;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {
	
	private static final Logger LOG=LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	
	@Autowired
	private JwtUserDetailService jwtUserDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String getJwtFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader(AppConstants.AUTH_HEADER);
		if (org.springframework.util.StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7, authHeader.length());
		}
		
		LOG.error("Request is not authenticated or dosen't contain Authorization token");
		return null;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwtToken = this.getJwtFromRequest(request);
	
		if (jwtToken != null && jwtTokenUtil.validateJwtToken(jwtToken)) {
			String username = jwtTokenUtil.extractUsername(jwtToken);
			UserDetails details = this.jwtUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
					details.getPassword(), details.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			
			LOG.info("Authenticated Successfully {}",details.getUsername());
			
		}
		

		
		
		filterChain.doFilter(request, response);
		

	}
}
