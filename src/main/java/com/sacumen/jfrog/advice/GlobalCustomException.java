package com.sacumen.jfrog.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.sacumen.jfrog.commons.AppConstants;
import com.sacumen.jfrog.response.ApiErrorResponse;

import io.jsonwebtoken.MalformedJwtException;

@RestControllerAdvice
public class GlobalCustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ApiErrorResponse>userNotFoundExc(){
		return new ResponseEntity<>(new ApiErrorResponse(AppConstants.USER_NOT_FOUND, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiErrorResponse> badCredException(){
		return new ResponseEntity<>(new ApiErrorResponse(AppConstants.BAD_CREDENTIALS, LocalDateTime.now()),HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MalformedJwtException.class)
	public ResponseEntity<ApiErrorResponse> malformedJwtException(){
		return new ResponseEntity<>(new ApiErrorResponse(AppConstants.MALFORMED_JWT, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<ApiErrorResponse> httpClientException(HttpClientErrorException ex){
		return new ResponseEntity<ApiErrorResponse>(new ApiErrorResponse(AppConstants.CLIENT_ERROR, LocalDateTime.now()),HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	
	
}
