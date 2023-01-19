package com.sacumen.jfrog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sacumen.jfrog.jwt.JwtAuthEntryPoint;
import com.sacumen.jfrog.jwt.JwtAuthTokenFilter;
import com.sacumen.jfrog.jwt.JwtUserDetailService;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig     {

	
	private String[] AUTH_WHITELIST = { "/auth/**" };
	
	
	@Autowired
	private JwtUserDetailService jwtUserDetailsService;
	
	  @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration,AuthenticationManagerBuilder authenticationManagerBuilder)
	            throws Exception {
		  authenticationManagerBuilder.userDetailsService(this.jwtUserDetailsService).passwordEncoder(this.passwordEncoder());
	        return authenticationConfiguration.getAuthenticationManager();
	    }
	

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() {
		return new JwtAuthTokenFilter();
	}

	@Bean 
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new JwtAuthEntryPoint();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
    @Bean
	public SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
	
		 httpSecurity.csrf().disable().authorizeHttpRequests().requestMatchers(AUTH_WHITELIST).permitAll().anyRequest().authenticated();
		
		httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

		return httpSecurity.build();

	}


}
