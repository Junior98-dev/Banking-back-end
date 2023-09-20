package com.junior.banking.validators;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.junior.banking.exceptions.ObjectValidationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Component
public class ObjectsValidator<T> {

	private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private final Validator validator = factory.getValidator();
	
	
	public void validate(T objectToValidate) {
		Set<ConstraintViolation<T>> violations =  validator.validate(objectToValidate);
		if(!violations.isEmpty()) {
			Set<String> errorMessages = violations.stream()
					.map(violation -> violation.getMessage())
					.collect(Collectors.toSet());
			
			// pour les erreurs de validations
			
			throw new ObjectValidationException(errorMessages, objectToValidate.getClass().getName());
		}
	}
}
