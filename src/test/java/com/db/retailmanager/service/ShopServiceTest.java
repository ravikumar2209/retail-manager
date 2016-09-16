package com.db.retailmanager.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.easymock.EasyMock;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import com.db.retailmanager.error.RetailManagerGeoCodingError;
import com.db.retailmanager.error.RetailManagerValidationError;
import com.db.retailmanager.model.Shop;
import com.db.retailmanager.model.ShopAddress;
import com.db.retailmanager.persistence.RetailManagerPersistenceManager;
import com.db.retailmanager.service.client.GeocodingClient;
import com.db.retailmanager.service.impl.ShopServiceImpl;
import com.db.retailmanager.util.NearbyShopCalculator;

public class ShopServiceTest {
	
	ShopService shopService;

	GeocodingClient mockGeoCodingClient;
	RetailManagerPersistenceManager mockPersistenceManager;
	NearbyShopCalculator mockNearbyShopCalculator;

	@Before
	public void setup()
	{
		shopService = new ShopServiceImpl();
		mockGeoCodingClient = EasyMock.createMock(GeocodingClient.class);
		mockNearbyShopCalculator = EasyMock.createMock(NearbyShopCalculator.class);
		mockPersistenceManager = EasyMock.createMock(RetailManagerPersistenceManager.class);
		
		Whitebox.setInternalState(shopService, GeocodingClient.class, mockGeoCodingClient);
		Whitebox.setInternalState(shopService, NearbyShopCalculator.class, mockNearbyShopCalculator);
		Whitebox.setInternalState(shopService, RetailManagerPersistenceManager.class, mockPersistenceManager);
	}
	
	@Test
	public void testAddShopWithValidData() {
		Shop shop = getShop();
		EasyMock.expect(mockGeoCodingClient.getLatitudeLongitude(EasyMock.anyObject(Shop.class))).andReturn(true);
		mockPersistenceManager.addShop(EasyMock.anyObject(Shop.class));
		EasyMock.expectLastCall();
		
		EasyMock.replay(mockGeoCodingClient, mockPersistenceManager);
		
		boolean success = shopService.addShop(shop);
		
		assertTrue(success);
		
		EasyMock.verify(mockGeoCodingClient, mockPersistenceManager);
	}
	
	@Test(expected = RetailManagerGeoCodingError.class)
	public void testAddShopWithGeoCodingError() {
		Shop shop = getShop();
		EasyMock.expect(mockGeoCodingClient.getLatitudeLongitude(EasyMock.anyObject(Shop.class))).andThrow(new RuntimeException());

		EasyMock.replay(mockGeoCodingClient);
		
		boolean success = shopService.addShop(shop);		
	}
	
	@Test(expected = RetailManagerGeoCodingError.class)
	public void testAddShopWithJSONError() {
		Shop shop = getShop();
		EasyMock.expect(mockGeoCodingClient.getLatitudeLongitude(EasyMock.anyObject(Shop.class))).andThrow(new JSONException("Unable to parse"));

		EasyMock.replay(mockGeoCodingClient);
		
		boolean success = shopService.addShop(shop);		
	}
	
	@Test
	public void testNearestShops()
	{
		List <Shop> listOfShops = new ArrayList();
		EasyMock.expect(mockNearbyShopCalculator.getNearest(EasyMock.anyDouble(), EasyMock.anyDouble(), EasyMock.anyObject(List.class))).andReturn(getShop());
		
		EasyMock.replay(mockNearbyShopCalculator);
		
		Shop shop = shopService.findNearestShop(20.0, 30.0);
		
		assertNotNull(shop);
	}
	
	@Test(expected = RetailManagerValidationError.class)
	public void testNearestShopsWithEmpty()
	{
		List <Shop> listOfShops = new ArrayList();
		EasyMock.expect(mockNearbyShopCalculator.getNearest(EasyMock.anyDouble(), EasyMock.anyDouble(), EasyMock.anyObject(List.class))).andReturn(null);		
		EasyMock.replay(mockNearbyShopCalculator);		
		Shop shop = shopService.findNearestShop(20.0, 30.0);		
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
