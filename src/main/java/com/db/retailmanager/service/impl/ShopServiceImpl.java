package com.db.retailmanager.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.db.retailmanager.error.RetailManagerGeoCodingError;
import com.db.retailmanager.error.RetailManagerValidationError;
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
	
	/* (non-Javadoc)
	 * @see com.db.retailmanager.service.ShopService#addShop(com.db.retailmanager.model.Shop)
	 */
	@Override
	public boolean addShop(Shop shop) {
		
		boolean success = false;
		try
		{
			success = geoCodingClient.getLatitudeLongitude(shop);
		} catch(JSONException jsonException)
		{
			throw new RetailManagerGeoCodingError("Error while parsing JSON result of geocoding");
		}		
		catch(Exception e)
		{
			throw new RetailManagerGeoCodingError("Problem in getting Geocoding value from Google");
		}
		if(success)
		{
			persistenceManager.addShop(shop);
		}
		
		return success;
	}	

	/* (non-Javadoc)
	 * @see com.db.retailmanager.service.ShopService#findNearestShop(java.lang.Double, java.lang.Double)
	 */
	@Override
	public Shop findNearestShop(Double latitude, Double longitude) {
		
		List<Shop> shopList = persistenceManager.getList();
		
		Shop shop = nearbyShopCalculator.getNearest(latitude, longitude, shopList);
		
		if(shop == null)
		{
			throw new RetailManagerValidationError("Currently there is no shops");
		}
		
		return shop;
		
	}

}
