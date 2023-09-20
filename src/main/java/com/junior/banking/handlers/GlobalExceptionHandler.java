package com.junior.banking.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.junior.banking.exceptions.ObjectValidationException;
import com.junior.banking.exceptions.OperationNomPermittedException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ObjectValidationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception) {
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Objet non validé")
				.errorSource(exception.getViolationSource())
				.validationErrors(exception.getViolations())
				.build();
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(representation);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception){
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(exception.getMessage())
				.build();
		return  ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(representation);
	}
	
	@ExceptionHandler(OperationNomPermittedException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(OperationNomPermittedException exception){
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage(exception.getErrorMsg())
				.build();
		return  ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
				.body(representation);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ExceptionRepresentation> handleException(){
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Il existe déja un utilisateur avec cet email")
				.build();
		return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(representation);
	}
	

	
}
