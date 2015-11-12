package com.iiitd.ap.lab10;

import java.util.Vector;

public class TemperatureSensor implements Runnable {
	private Vector<Observer> observers;
	private TemperatureLog state;
	public TemperatureSensor(double temp,String city)
	{
		observers = new Vector<>();
		state = new TemperatureLog(temp,city);
	}
	
	public TemperatureLog getState()
	{
		return this.state;
	}
	
	public void register(Observer observer)
	{
		this.observers.add(observer);
	}
	
	private void pingObservers()
	{
		for(Observer observer : observers) observer.update();
	}
	
	private void updateState(double new_temp)
	{
		this.state.setTemperature(new_temp);
		this.pingObservers();
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				Thread.sleep(5000);
				this.updateState(MainClass.getRandomTemp());
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
