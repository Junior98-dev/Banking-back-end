package com.junior.banking.handlers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
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
	@ExceptionHandler(DisabledException.class)
	public ResponseEntity<ExceptionRepresentation> handleDisabledException(){
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Vous ne pouvez pas accéder à votre compte, car il n'est pas encore activé")
				.build();
		return  ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(representation);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionRepresentation> handleBadCredentialException(){
		ExceptionRepresentation representation = ExceptionRepresentation.builder()
				.errorMessage("Email ou mot de passe incorrect")
				.build();
		return  ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(representation);
	}
	

	
}
