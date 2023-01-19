package com.sacumen.jfrog.jwt;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sacumen.jfrog.commons.AppConstants;

@Service
public class JwtUserDetailService implements UserDetailsService {

	@Value("${user.encrypted.password}")
	private String encryptedPassword;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (AppConstants.USERNAME.equals(username)) {
			return  new User(AppConstants.USERNAME,
					encryptedPassword,
					new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(AppConstants.ROLE_ADMIN),
							new SimpleGrantedAuthority(AppConstants.ROLE_USER),
							new SimpleGrantedAuthority(AppConstants.ROLE_ADMINISTER_THE_PLATFORM))));
		

		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
