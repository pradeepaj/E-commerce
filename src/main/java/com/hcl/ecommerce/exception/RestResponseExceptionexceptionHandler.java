package com.hcl.ecommerce.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseExceptionexceptionHandler {


	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request){
		ErrorResponse err_res=new ErrorResponse(new Date(), HttpStatus.OK, ex.getMessage());
		return new ResponseEntity<>(err_res,HttpStatus.NOT_FOUND);
	}	
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> handleProductNotFoundException(Exception ex, WebRequest request){
		ErrorResponse err_res=new ErrorResponse(new Date(), HttpStatus.OK, ex.getMessage());
		return new ResponseEntity<>(err_res,HttpStatus.NOT_FOUND);
	}	

	
	

	

	
}
