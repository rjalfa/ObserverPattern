package com.iiitd.ap.lab10;

import com.iiitd.ap.lab10.observers.GenerateStats;
import com.iiitd.ap.lab10.observers.SerializeTemperature;
import com.iiitd.ap.lab10.observers.TemperaturePredictor1;
import com.iiitd.ap.lab10.observers.TemperaturePredictor2;

public class MainClass {
	
	public static double getRandomTemp()
	{
		return 35.0;
	}
	
	public static void main(String[] args)
	{
		Observer[] observers = new Observer[4];
		observers[0] = new GenerateStats();
		observers[1] = new SerializeTemperature();
		observers[2] = new TemperaturePredictor1();
		observers[3] = new TemperaturePredictor2();
		TemperatureSensor[] sensors = new TemperatureSensor[3];
		sensors[0] = new TemperatureSensor(35,"Delhi");
		sensors[1] = new TemperatureSensor(35,"Mumbai");
		sensors[2] = new TemperatureSensor(35,"Srinagar");
		for(TemperatureSensor i : sensors) for(Observer j : observers)
		{
			i.register(j);
			j.subject = i;
		}
		//Running the application
		for(TemperatureSensor i : sensors)
		{
			(new Thread(i)).start();
		}
		//(new Thread(sensors[0])).start();
	}
}
