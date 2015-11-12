package com.iiitd.ap.lab10;

import java.util.HashMap;
import java.util.Vector;

public abstract class Observer {
	protected TemperatureSensor subject;
	protected Vector<TemperatureLog> states;
	protected static HashMap<Integer,String> city_map = new HashMap<>();
	protected TemperatureLog temp_log;
	public abstract void update(int id);
	
	static{
		city_map.put(0, "Delhi");
		city_map.put(1, "Mumbai");
		city_map.put(2, "Srinagar");
	}
}
