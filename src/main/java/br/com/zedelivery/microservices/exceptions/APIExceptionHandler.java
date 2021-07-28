package br.com.zedelivery.microservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.gson.Gson;

@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Gson gson = new Gson();

	@ExceptionHandler(EntityNotExistsException.class)
	public ResponseEntity<Object> handlerEntityNotExists(EntityNotExistsException ex) {
		return new ResponseEntity<>(gson.toJson(ex.getLocalizedMessage()), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handlerBadRequestException(BadRequestException ex) {
		return new ResponseEntity<>(gson.toJson(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> handlerInternalServerErrorException(InternalServerErrorException ex) {
		return new ResponseEntity<>(gson.toJson(ex.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
