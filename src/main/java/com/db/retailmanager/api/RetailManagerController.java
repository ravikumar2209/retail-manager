package com.db.retailmanager.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.retailmanager.error.RetailManagerValidationError;
import com.db.retailmanager.model.Shop;
import com.db.retailmanager.service.ShopService;


/**
 * @author ravvenkatara
 * 
 *  This Class has the entry point for Retail Manager Shop REST APIs
 *
 */
/**
 * @author ravvenkatara
 *
 */
@RestController
@RequestMapping(value = "/retail-manager")
public class RetailManagerController {
	
	@Autowired
	ShopService shopService;
	
	
	/**
	 * This API adds a Shop to the in memory datastructure after fetching the lat and long
	 * @param shop - details of shop to be added
	 * @return 200 OKAY in case of success
	 */
	@RequestMapping(value = "shop/add", method = RequestMethod.POST)
	public ResponseEntity addShop(@Valid @RequestBody Shop shop) {
		//Asserts
		if(shop == null || shop.getShopAddress() == null || shop.getShopName() == null)
		{
			throw new RetailManagerValidationError("Either Shop name or Address is null");
		}		
		
		shopService.addShop(shop);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * This API tries to fetch the nearest shop given a latitude and longitude
	 * @param longitude - longitude of the location
	 * @param latitude - latitude of the location
	 * @return Shop details
	 */
	@RequestMapping(value = "shop/findNearest", method = RequestMethod.GET)
	public @ResponseBody Shop findNearest(@RequestParam(value="lng", defaultValue="0") Double longitude, @RequestParam(value="lat", defaultValue="0") Double latitude)
	{	
		if(latitude == 0 || longitude == 0)
		{
			throw new RetailManagerValidationError("Either Latitude or Longitude is zero");
		}
		
		Shop shop = shopService.findNearestShop(latitude, longitude);
		
		return shop;
	}
}