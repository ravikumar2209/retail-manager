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

import com.db.retailmanager.model.Shop;
import com.db.retailmanager.service.ShopService;

@RestController
@RequestMapping(value = "/retail-manager")
public class RetailManagerController {
	
	@Autowired
	ShopService shopService;
	
	@RequestMapping(value = "shop/add", method = RequestMethod.POST)
	public ResponseEntity addShop(@Valid @RequestBody Shop shop) {

		shopService.addShop(shop);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "shop/findNearest", method = RequestMethod.GET)
	public @ResponseBody Shop findNearest(@RequestParam(value="lng", defaultValue="0") Double longitude, @RequestParam(value="lat", defaultValue="0") Double latitude)
	{					
		Shop shop = shopService.findNearestShop(latitude, longitude);
		
		return shop;
	}
}