package br.com.zedelivery.microservices.exceptions;

public class EntityNotExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntityNotExistsException(String message) {
		super(message);
	}

}
