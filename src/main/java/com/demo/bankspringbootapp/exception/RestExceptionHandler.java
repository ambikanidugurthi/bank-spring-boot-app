package com.demo.bankspringbootapp.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{


    /**
     * Handles EntityNotFoundException. Created to encapsulate errors with more detail than javax.persistence.EntityNotFoundException.
     *
     * @param ex the EntityNotFoundException
     * @return the ApiError object
     */
    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiError apiError = new ApiError(NOT_FOUND, ex);
        return buildResponseEntity(apiError);
    }

	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<Object>(apiError,apiError.getStatus());
	}
	
}
