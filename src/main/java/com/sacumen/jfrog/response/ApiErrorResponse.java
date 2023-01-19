package com.sacumen.jfrog.response;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiErrorResponse {

	private String message;
	private LocalDateTime timestamp;
	//errorCode
	
}
