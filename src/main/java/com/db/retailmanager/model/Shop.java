package com.db.retailmanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Resource represents Shop.
 */
@com.fasterxml.jackson.annotation.JsonInclude(value=com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
@com.fasterxml.jackson.annotation.JsonPropertyOrder(value={"shopName","shopAddress"})
public class Shop {

	@com.fasterxml.jackson.annotation.JsonProperty(value="shopName")
	@javax.validation.constraints.NotNull
	private String shopName;
	
	@com.fasterxml.jackson.annotation.JsonProperty(value="shopAddress")	
	@javax.validation.constraints.NotNull
	private ShopAddress shopAddress;
	
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	public void setShopLatitude(double shopLatitude) {
		this.shopLatitude = shopLatitude;
	}

	public void setShopLongitude(double shopLongitude) {
		this.shopLongitude = shopLongitude;
	}

	@JsonIgnore
	private double shopLatitude;
	
	@JsonIgnore
	private double shopLongitude;
	
	public String getShopName() {
		return shopName;
	}

	public ShopAddress getShopAddress() {
		return shopAddress;
	}

	public double getShopLatitude() {
		return shopLatitude;
	}

	public double getShopLongitude() {
		return shopLongitude;
	}

	public String toString()
	{
		return shopName+ shopAddress.toString();
	}
 }
