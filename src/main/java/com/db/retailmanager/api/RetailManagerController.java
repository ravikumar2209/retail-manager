package com.db.retailmanager.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.db.retailmanager.model.Shop;
import com.db.retailmanager.service.AddShopService;

@RestController
@RequestMapping(value = "/retail-manager")
public class RetailManagerController {
	
	@Autowired
	AddShopService addShopService;
	
	@RequestMapping(value = "shop/add", method = RequestMethod.POST)
	public ResponseEntity addShop(@Valid @RequestBody Shop shop) {

		addShopService.addShop(shop);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}