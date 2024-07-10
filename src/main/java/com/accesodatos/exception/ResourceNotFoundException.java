package com.accesodatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class is used to display an error when 
 * the response of an API is not found.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3510175088574375864L;

	
	 public ResourceNotFoundException() {
		        super();
	  }
	
	  public ResourceNotFoundException(String message) {
	      super(message);
	  }
	
	  public ResourceNotFoundException(String message, Throwable cause) {
	      super(message, cause);
	  }
	

}
