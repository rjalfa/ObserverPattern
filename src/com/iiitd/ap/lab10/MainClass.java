package com.iiitd.ap.lab10;

/*
 * @Rounaq Jhunjhunu Wala - 2014089
 * @Shrey Bagroy - 2014099
 */


import com.iiitd.ap.lab10.observers.GenerateStats;
import com.iiitd.ap.lab10.observers.SerializeTemperature;
import com.iiitd.ap.lab10.observers.TemperaturePredictor1;
import com.iiitd.ap.lab10.observers.TemperaturePredictor2;

public class MainClass {
	private static double rand_time;
	private static final int WAIT_TIME = 5000;
	public static double getRandomTemp()
	{
		rand_time = ((System.nanoTime())%5100)/100.00;
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
		
		for(int id=0;id<3;id++)
			sensor.pingObservers(id);
		//Running the application
		
		Thread t1 = (new Thread(new Runnable()
			{
				public void run()
				{
					while(true)
					{	
						try {
							Thread.sleep(WAIT_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						sensor.updateState(MainClass.getRandomTemp(),0);
					}
				}
			}
		));
		Thread t2 = (new Thread(new Runnable()
			{
				public void run()
				{
					while(true)
					{
						try {
							Thread.sleep(WAIT_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						sensor.updateState(MainClass.getRandomTemp(),1);
					}
				}
			}
		));
		Thread t3 = (new Thread(new Runnable()
			{
				public void run()
				{
					while(true)
					{
						try {
							Thread.sleep(WAIT_TIME);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						sensor.updateState(MainClass.getRandomTemp(),2);
					}
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
