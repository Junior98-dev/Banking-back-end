package com.junior.banking.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class OperationNomPermittedException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String errorMsg;
	
	private final String operationId;
	
	private final String source;
	
	private final String dependency;
}
