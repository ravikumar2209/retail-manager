package com.db.retailmanager.service.client;

import javax.inject.Named;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.db.retailmanager.api.RetailManagerController;
import com.db.retailmanager.conf.RetailManagerProperties;
import com.db.retailmanager.constants.*;
import com.db.retailmanager.model.Shop;

/**
 * @author ravvenkatara
 *	Has the REST client implementation for connecting to google's geocoding API
 */
@Named
public class GeocodingClient {

	@Autowired
	RetailManagerProperties prop;

	private static final Logger LOGGER = LoggerFactory.getLogger(GeocodingClient.class);
	
	public boolean getLatitudeLongitude(Shop shop) {

		boolean success = true;
		RestTemplate restTemplate = new RestTemplate();

		StringBuilder uri = new StringBuilder();
		uri.append(RetailManagerConstants.GOOGLE_GEO_CODE_URI)
				.append("?address=").append(shop.getShopAddress().getNumber())
				.append(shop.getShopAddress().getPostCode()).append("&key=")
				.append(prop.getKey());

		String result = restTemplate.getForObject(uri.toString(), String.class);

		JSONObject geoCodingResponseJson = new JSONObject(result);
		
		JSONObject geoCodingResultJSON = geoCodingResponseJson.getJSONArray(
				RetailManagerConstants.JSON_ELEMENT_RESULTS).getJSONObject(0);

		JSONObject geometry = geoCodingResultJSON
				.getJSONObject(RetailManagerConstants.JSON_ELEMENT_GEOMETRY);
		JSONObject location = geometry
				.getJSONObject(RetailManagerConstants.JSON_ELEMENT_LOCATION);

		double latitude = location
				.getDouble(RetailManagerConstants.JSON_ELEMENT_LATITUDE);
		double longitude = location
				.getDouble(RetailManagerConstants.JSON_ELEMENT_LONGITUDE);

		shop.setShopLatitude(latitude);
		shop.setShopLongitude(longitude);
		
		LOGGER.debug("Successfully retrieved latitude and longitude");
		return success;
	}
}
