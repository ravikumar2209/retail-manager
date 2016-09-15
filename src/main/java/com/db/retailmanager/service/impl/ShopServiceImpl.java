package com.db.retailmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.db.retailmanager.model.Shop;
import com.db.retailmanager.persistence.RetailManagerPersistenceManager;
import com.db.retailmanager.service.ShopService;
import com.db.retailmanager.service.client.GeocodingClient;
import com.db.retailmanager.util.NearbyShopCalculator;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Inject
	GeocodingClient geoCodingClient;
	
	@Inject
	RetailManagerPersistenceManager persistenceManager;
	
	@Inject
	NearbyShopCalculator nearbyShopCalculator;
	
	@Override
	public boolean addShop(Shop shop) {
		
		boolean success = geoCodingClient.getLatitudeLongitude(shop);
		if(success)
		{
			persistenceManager.addShop(shop);
		}
		
		return success;
	}	

	@Override
	public Shop findNearestShop(Double latitude, Double longitude) {
		
		List<Shop> shopList = persistenceManager.getList();
		
		return nearbyShopCalculator.getNearest(latitude, longitude, shopList);		
		
	}

}
