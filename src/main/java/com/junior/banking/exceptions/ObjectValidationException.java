package com.junior.banking.exceptions;

import java.util.Set;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObjectValidationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private final Set<String> violations;
	
	@Getter
	private final String violationSource;
	
}
