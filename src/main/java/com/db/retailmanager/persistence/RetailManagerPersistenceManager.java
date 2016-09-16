package com.db.retailmanager.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Named;

import com.db.retailmanager.model.Shop;

/**
 * @author ravvenkatara
 *
 *	Abstracts how the data is stored.
 */
@Named
public class RetailManagerPersistenceManager {

	List<Shop> shopList = Collections.synchronizedList(new ArrayList<Shop>());
	
	public void addShop(Shop shop)
	{
		shopList.add(shop);
	}
	
	public List<Shop> getList()
	{
		return shopList;
	}
	
}
