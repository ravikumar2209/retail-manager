package com.db.retailmanager.service;

import com.db.retailmanager.model.Shop;

public interface ShopService {

	boolean addShop(Shop shop);
	
	Shop findNearestShop(Double latitude, Double longitude);
}
