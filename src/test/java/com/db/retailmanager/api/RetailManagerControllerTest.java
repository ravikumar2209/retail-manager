package com.db.retailmanager.api;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.db.retailmanager.error.RetailManagerGeoCodingError;
import com.db.retailmanager.error.RetailManagerValidationError;
import com.db.retailmanager.model.Shop;
import com.db.retailmanager.model.ShopAddress;
import com.db.retailmanager.service.ShopService;

public class RetailManagerControllerTest {
	
	RetailManagerController controller;
	ShopService mockShopService;
	
	@Before
	public void setup()
	{
		controller = new RetailManagerController();
		mockShopService = EasyMock.createMock(ShopService.class);
		Whitebox.setInternalState(controller, ShopService.class, mockShopService);
	}
	

	@Test
	public void testAddShopWithValidDetails() {
		Shop shop = getShop();
		
		EasyMock.expect(mockShopService.addShop(EasyMock.anyObject(Shop.class))).andReturn(true);
		EasyMock.replay(mockShopService);		
		ResponseEntity<Void> response = controller.addShop(shop);
		
		assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
		
		EasyMock.verify(mockShopService);
	}
	
	@Test(expected=RetailManagerValidationError.class)
	public void testAddShopWithNullAddress() {
		Shop shop = getShop();
		shop.setShopAddress(null);
		EasyMock.expect(mockShopService.addShop(EasyMock.anyObject(Shop.class))).andReturn(true);
		EasyMock.replay(mockShopService);		
		ResponseEntity<Void> response = controller.addShop(shop);
		
	}
	
	@Test(expected=RetailManagerValidationError.class)
	public void testAddShopWithNullShopName() {
		Shop shop = getShop();
		shop.setShopName(null);		
		EasyMock.expect(mockShopService.addShop(EasyMock.anyObject(Shop.class))).andReturn(true);
		EasyMock.replay(mockShopService);		
		ResponseEntity<Void> response = controller.addShop(shop);
		
	}

	@Test(expected=RetailManagerGeoCodingError.class)
	public void testAddShopWithGeoCodingError() {
		Shop shop = getShop();				
		EasyMock.expect(mockShopService.addShop(EasyMock.anyObject(Shop.class))).andThrow(new RetailManagerGeoCodingError());
		EasyMock.replay(mockShopService);		
		ResponseEntity<Void> response = controller.addShop(shop);
		EasyMock.verify(mockShopService);
		
	}
	
	@Test
	public void testFindNearestForValidLatLng() {		

		Shop shop = getShop();
		EasyMock.expect(mockShopService.findNearestShop(EasyMock.anyDouble(), EasyMock.anyDouble())).andReturn(shop);
		EasyMock.replay(mockShopService);		
		Shop Shop = controller.findNearest(12.45, 23.34);
		
		assertNotNull(shop);
		EasyMock.verify(mockShopService);
		
	}
	
	@Test(expected = RetailManagerValidationError.class)
	public void testFindNearestForInvalidLatLng() {		

		Shop shop = getShop();
		EasyMock.expect(mockShopService.findNearestShop(EasyMock.anyDouble(), EasyMock.anyDouble())).andReturn(shop);
		EasyMock.replay(mockShopService);		
		Shop Shop = controller.findNearest(0.0, 23.34);		
		
	}
	
	private Shop getShop()
	{
		Shop shop = new Shop();		
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber("1500, XYZ street, CA");
		shopAddress.setPostCode(560034L);
		shop.setShopName("ABC");
		shop.setShopAddress(shopAddress);
		return shop;
	}

}
