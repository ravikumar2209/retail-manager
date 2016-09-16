package com.db.retailmanager.service;

import com.db.retailmanager.model.Shop;

/**
 * @author ravvenkatara
 * 
 * This has the service layer APIs for shop.
 *
 */
public interface ShopService {

	/**
	 * This service API tries to add the shop to the in-memory DB.
	 * @param shop - details of shop to be added
	 * @return true on success
	 */
	boolean addShop(Shop shop);
	
	/**
	 * This service tries to fetch the nearest shop based on latitude and longitude
	 * @param latitude - latitude of the location
	 * @param longitude - longitude of the location
	 * @return - nearest Shop 
	 */
	Shop findNearestShop(Double latitude, Double longitude);
}
