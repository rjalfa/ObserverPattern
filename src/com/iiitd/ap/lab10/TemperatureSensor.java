package com.iiitd.ap.lab10;

import java.util.Vector;

public class TemperatureSensor {
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
	
	public synchronized Vector<TemperatureLog> getStates()
	{
		return this.states;
	}
	
	public synchronized void register(Observer observer)
	{
		this.observers.add(observer);
	}
	
	public synchronized void pingObservers()
	{
		for(Observer observer : observers) observer.update();
	}
}
