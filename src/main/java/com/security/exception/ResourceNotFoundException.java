package com.security.exception;

	public class ResourceNotFoundException extends RuntimeException {
	    public ResourceNotFoundException(String message) {
	        super(message); // ✔️ Properly passes the message to the superclass
	    }





}
