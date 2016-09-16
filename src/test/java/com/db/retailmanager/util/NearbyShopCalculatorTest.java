package com.db.retailmanager.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.db.retailmanager.model.Shop;
import com.db.retailmanager.model.ShopAddress;

public class NearbyShopCalculatorTest {
	
	NearbyShopCalculator nearbyShopCalculator;
	
	@Before
	public void setup()
	{
		nearbyShopCalculator = new NearbyShopCalculator();
	}

	@Test
	public void test() {
		Shop shop1 = getShop();
		
		Shop shop2 = new Shop();
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber("144, Hamilton street, RU");
		shopAddress.setPostCode(560034L);
		shop2.setShopName("EDC");
		shop2.setShopAddress(shopAddress);
		shop2.setShopLatitude(10.0);
		shop2.setShopLongitude(10.0);
		
		List <Shop>listOfShops = new ArrayList();
		listOfShops.add(shop1);
		listOfShops.add(shop2);
		
		Shop shopNearest = nearbyShopCalculator.getNearest(100.0, 100.0, listOfShops);
		
		assertEquals(shop1, shopNearest);
		
	}

	private Shop getShop()
	{
		Shop shop = new Shop();		
		ShopAddress shopAddress = new ShopAddress();
		shopAddress.setNumber("1500, XYZ street, CA");
		shopAddress.setPostCode(560034L);
		shop.setShopName("ABC");
		shop.setShopAddress(shopAddress);
		shop.setShopLatitude(110.0);
		shop.setShopLongitude(120.0);
		return shop;
	}
}
