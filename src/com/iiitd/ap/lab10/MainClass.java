package com.iiitd.ap.lab10;

import java.util.Calendar;

import com.iiitd.ap.lab10.observers.GenerateStats;
import com.iiitd.ap.lab10.observers.SerializeTemperature;
import com.iiitd.ap.lab10.observers.TemperaturePredictor1;
import com.iiitd.ap.lab10.observers.TemperaturePredictor2;

public class MainClass {
	private static double rand_time;
	
	public static double getRandomTemp()
	{
		rand_time = (System.nanoTime())%151;
		System.out.println("Randomly generated temperature: " + rand_time);
		return rand_time;
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
		
		Thread t1 = (new Thread(new Runnable()
			{
				public void run()
				{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sensor.updateState(MainClass.getRandomTemp(),0);
				}
			}
		));
		Thread t2 = (new Thread(new Runnable()
			{
				public void run()
				{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sensor.updateState(MainClass.getRandomTemp(),1);
				}
			}
		));
		Thread t3 = (new Thread(new Runnable()
			{
				public void run()
				{
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					sensor.updateState(MainClass.getRandomTemp(),0);
				}
			}
		));
		t1.start();
		Thread.sleep(1000);
		t2.start();
		Thread.sleep(1000);
		t3.start();
	}
}
