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
	
	public static void main(String[] args) throws InterruptedException
	{
		Observer[] observers = new Observer[4];
		observers[0] = new GenerateStats();
		observers[1] = new SerializeTemperature();
		observers[2] = new TemperaturePredictor1();
		observers[3] = new TemperaturePredictor2();
		TemperatureSensor sensor = new TemperatureSensor(35);
		for(Observer j : observers)
		{
			sensor.register(j);
			j.subject = sensor;
		}
		//Running the application
		(new Thread(sensor)).start();
	}
}
