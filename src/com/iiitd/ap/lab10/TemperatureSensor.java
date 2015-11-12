package com.iiitd.ap.lab10;

/*
 * @Rounaq Jhunjhunu Wala - 2014089
 * @Shrey Bagroy - 2014099
 */

import java.util.Vector;

public class TemperatureSensor implements Subject {
	private Vector<Observer> observers;
	private Vector<TemperatureLog> states;
	public TemperatureSensor(double temp)
	{
		observers = new Vector<>();
		states = new Vector<>();
		states.add(new TemperatureLog(temp,"Delhi"));
		states.add(new TemperatureLog(temp,"Mumbai"));
		states.add(new TemperatureLog(temp,"Srinagar"));
	}
	
	public Vector<TemperatureLog> getStates()
	{
		return this.states;
	}
	
	public void register(Observer observer)
	{
		this.observers.add(observer);
	}
	
	public void pingObservers(int i)
	{
		for(Observer observer : observers) observer.update(i);
	}
	
	void updateState(double new_temp,int i)
	{
		this.states.get(i).setTemperature(new_temp);
		this.pingObservers(i);
	}

}
