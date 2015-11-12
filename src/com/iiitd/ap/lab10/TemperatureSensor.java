package com.iiitd.ap.lab10;

import java.util.Vector;

public class TemperatureSensor implements Runnable {
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
	
	private void pingObservers()
	{
		for(Observer observer : observers) observer.update();
	}
	
	private void updateState(double new_temp,int i)
	{
		this.states.get(i).setTemperature(new_temp);
	}

	@Override
	public void run() {
		try {
			while(true)
			{
				Thread.sleep(5000);
				this.updateState(MainClass.getRandomTemp(),0);
				this.updateState(MainClass.getRandomTemp(),1);
				this.updateState(MainClass.getRandomTemp(),2);
				this.pingObservers();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
