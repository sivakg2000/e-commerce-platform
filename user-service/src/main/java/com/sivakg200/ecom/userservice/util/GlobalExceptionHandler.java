package com.sivakg200.ecom.userservice.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice; 
import io.micrometer.core.instrument.config.validate.ValidationException;
import jakarta.servlet.http.HttpServletRequest; 

import com.sivakg200.ecom.userservice.model.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse<Void>> handleException(HttpServletRequest req,Exception ex){
		List<String> errors=Arrays.asList(ex.getMessage());
		APIResponse<Void> resp=ResponseUtil.error(errors, "An error occurred", 1000, req.getRequestURI());
		return new ResponseEntity<APIResponse<Void>>(resp,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<APIResponse<Void>> handleResourceNotException(HttpServletRequest req,Exception ex){
		List<String> errors=Arrays.asList(ex.getMessage());
		APIResponse<Void> resp=ResponseUtil.error(errors, "An error occurred", 1001, req.getRequestURI());
		return new ResponseEntity<APIResponse<Void>>(resp,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<APIResponse<Void>> handleValidationException(HttpServletRequest req,Exception ex){
		List<String> errors=Arrays.asList(ex.getMessage());
		APIResponse<Void> resp=ResponseUtil.error(errors, "Validation Failed", 1002, req.getRequestURI());
		return new ResponseEntity<APIResponse<Void>>(resp,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<APIResponse<Void>> handleDuplicateKeyException(HttpServletRequest req,Exception ex){
		List<String> errors=Arrays.asList(ex.getMessage());
		APIResponse<Void> resp=ResponseUtil.error(errors, "Duplicate Key", 1003, req.getRequestURI());
		return new ResponseEntity<APIResponse<Void>>(resp,HttpStatus.BAD_REQUEST);
	}

}
