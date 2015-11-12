package com.iiitd.ap.lab10;

import java.util.Vector;

public class TemperatureSensor implements Subject {
	private Vector<Observer> observers;
	private Vector<TemperatureLog> states;
	public TemperatureSensor(double temp)
	{
		observers = new Vector<>();
		states = new Vector<>();
		states.add(new TemperatureLog(temp,"Delhi"));
		pingObservers(0);
		states.add(new TemperatureLog(temp,"Mumbai"));
		pingObservers(1);
		states.add(new TemperatureLog(temp,"Srinagar"));
		pingObservers(2);
	}
	
	public Vector<TemperatureLog> getStates()
	{
		return this.states;
	}
	
	public void register(Observer observer)
	{
		this.observers.add(observer);
	}
	
	private void pingObservers(int i)
	{
		for(Observer observer : observers) observer.update(i);
	}
	
	void updateState(double new_temp,int i)
	{
		this.states.get(i).setTemperature(new_temp);
		this.pingObservers(i);
	}

}
