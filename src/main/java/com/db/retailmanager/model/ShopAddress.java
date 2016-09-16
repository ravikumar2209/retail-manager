package com.db.retailmanager.model;

/**
 * @author ravvenkatara
 * Resource represents shop Address
 */
public class ShopAddress {
	
	@com.fasterxml.jackson.annotation.JsonProperty(value="number")
	private String number;
	
	@com.fasterxml.jackson.annotation.JsonProperty(value="postCode")
	private Long postCode;
	
	public void setNumber(String number) {
		this.number = number;
	}

	public void setPostCode(Long postCode) {
		this.postCode = postCode;
	}

	public String getNumber() {
		return number;
	}

	public Long getPostCode() {
		return postCode;
	}

	public String toString()
	{
		return " "+number+" "+postCode;
	}
}
