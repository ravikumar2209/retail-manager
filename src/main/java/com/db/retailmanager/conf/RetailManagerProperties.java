package com.db.retailmanager.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("retailmanager")
public class RetailManagerProperties {

	private String key = "AIzaSyCg6Pkjtlepy7PdznV7vo16IcwrFgAOfs0";
	
	public String getKey()
	{
		return key;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
}
