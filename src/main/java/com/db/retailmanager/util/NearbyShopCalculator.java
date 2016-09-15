package com.db.retailmanager.util;

import java.util.List;

import javax.inject.Named;

import org.springframework.util.CollectionUtils;

import com.db.retailmanager.model.Shop;

@Named
public class NearbyShopCalculator {

	public Shop getNearest(Double lat, Double lng, List<Shop> shopList) {
		Shop nearestShop = null;

		if (!CollectionUtils.isEmpty(shopList)) {

			double closestDistance = Double.MAX_VALUE;

			for (Shop shop : shopList) {

				double distance = getDistance(lat, lng, shop.getShopLatitude(),
						shop.getShopLongitude());
				if (distance < closestDistance) {
					nearestShop = shop;
				}
			}
		}

		return nearestShop;

	}

	/*
	 * private double getDistance(Double lat1, Double lng1, Double lat2, Double
	 * lng2) {
	 * 
	 * }
	 */

	private double getDistance(double lat1, double lon1, double lat2,
			double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		dist = dist * 1.609344;

		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
