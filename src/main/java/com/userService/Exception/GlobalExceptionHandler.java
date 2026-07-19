package com.userService.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.userService.Payload.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)   //this annotation is used in case the project if this Exception is  occured then this method is called 
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e){
		
		String message=e.getMessage();
		ApiResponse response = new ApiResponse(message, false, null);
		response.setMessage(message);
		response.setSuccess(false);
		response.setStatus(HttpStatus.NOT_FOUND);
				         
		
		return new ResponseEntity<ApiResponse>(response,HttpStatus.NOT_FOUND);
		
	}

}
