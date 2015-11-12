package com.iiitd.ap.lab10;

import java.util.Vector;

public class TemperatureSensor implements Runnable {
	private Vector<Observer> observers;
	private Vector<TemperatureLog> states;
	public TemperatureSensor()
	{
		observers = new Vector<>();
		states = new Vector<>();
		states.add(new TemperatureLog(35,"Delhi"));
		states.add(new TemperatureLog(35,"Mumbai"));
		states.add(new TemperatureLog(35,"Srinagar"));
	}
	
	public Vector<TemperatureLog> getStates()
	{
		return this.states;
	}
	
	public void register(Observer observer)
	{
		this.observers.add(observer);
	}
	
	public void pingObservers()
	{
		for(Observer observer : observers) observer.update();
	}
	
	private void updateState()
	{
		//..
		this.pingObservers();
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				Thread.sleep(5);
				this.updateState();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
