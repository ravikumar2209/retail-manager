package com.db.retailmanager.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.db.retailmanager.model.Shop;
import com.db.retailmanager.persistence.RetailManagerPersistenceManager;
import com.db.retailmanager.service.AddShopService;
import com.db.retailmanager.service.client.GeocodingClient;

@Service
public class AddShopServiceImpl implements AddShopService{
	
	@Inject
	GeocodingClient geoCodingClient;
	
	@Inject
	RetailManagerPersistenceManager persistenceManager;
	
	@Override
	public boolean addShop(Shop shop) {
		
		boolean success = geoCodingClient.getLatitudeLongitude(shop);
		if(success)
		{
			persistenceManager.addShop(shop);
		}
		
		return success;
	}

}
