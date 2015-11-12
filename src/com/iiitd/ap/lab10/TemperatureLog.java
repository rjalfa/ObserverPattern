package com.iiitd.ap.lab10;

/*
 * @Rounaq Jhunjhunu Wala - 2014089
 * @Shrey Bagroy - 2014099
 */

import java.io.Serializable;

public class TemperatureLog implements Serializable {
	private static final long serialVersionUID = -7507680477865452959L;
	
	private double temperature;
	private String city;
	
	public TemperatureLog(double temperature, String city) {
		this.temperature = temperature;
		this.city = city;
	}
	
	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
