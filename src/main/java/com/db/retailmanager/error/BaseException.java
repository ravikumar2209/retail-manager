package com.db.retailmanager.error;

/**
 * @author ravvenkatara
 * 
 *  Abstracts all User defined exceptions of RetailManager
 */
public class BaseException extends RuntimeException{

	String message;
	
	public BaseException() {
		super();
	}
	
	public BaseException(String message)
	{
		this.message = message;
	}
	
	public String getMessageDetail()
	{
		return message;
	}
}
