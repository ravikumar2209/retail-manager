package com.db.retailmanager.error;

/**
 * @author ravvenkatara
 *
 *	This class abstracts all the validation errors.
 */
public class RetailManagerValidationError extends BaseException {
		
	public RetailManagerValidationError() {
		super();
	}
	
	public RetailManagerValidationError(String message)
	{
		super(message);
		
	}
}
