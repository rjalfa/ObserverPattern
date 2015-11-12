package com.iiitd.ap.lab10.observers;

import java.util.Random;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;
import com.iiitd.ap.lab10.TemperatureLog;

public class TemperaturePredictor2 extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(5);	
	
	private TemperatureLog state;
	private double max;
	private double min;
	private double res;
	private Random r;
	
	@Override
	public synchronized void update() {
		state = this.subject.getState();
		switch(state.getCity())
		{
			case "Delhi" : delhi_temp.add(state.getTemperature()); break;
			case "Mumbai" : mum_temp.add(state.getTemperature()); break;
			case "Srinagar" : sri_temp.add(state.getTemperature()); break;
		}
		calcTemp("Delhi", delhi_temp); 
		calcTemp("Mumbai", mum_temp); 
		calcTemp("Srinagar", sri_temp); 
		System.out.println("---------------");
		System.out.println();
	}
	
	public void calcTemp(String city, CircularFifoQueue<Double> city_temp)
	{
		r = new Random();
		min = 99999;
		max=-99999;
		
		for(double temp: city_temp )
		{
			if(temp <= min)
				temp=min;
			if(temp >= max)
				temp=max;
		}
		
		res = min + (max-min)*r.nextDouble();
		System.out.println("Predicted temperature (by TemperaturePredictor2) for " + city + " is: " + res);
	}

}
