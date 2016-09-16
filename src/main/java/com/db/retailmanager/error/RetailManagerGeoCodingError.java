package com.db.retailmanager.error;

/**
 * @author ravvenkatara
 * 
 * This Exception class describes all the errors relevant to GeoCoding
 *
 */
public class RetailManagerGeoCodingError extends BaseException {

	public RetailManagerGeoCodingError() {
		super();
	}
	
	public RetailManagerGeoCodingError(String message)
	{
		super(message);
		
	}
}
