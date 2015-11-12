package com.iiitd.ap.lab10.observers;

import java.util.Random;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;
import com.iiitd.ap.lab10.TemperatureLog;

public class TemperaturePredictor2 extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(5);	
	
	private double max;
	private double min;
	private double res;
	private Random r;
	
	@Override
	public synchronized void update(int id) {
		states = this.subject.getStates();
		temp_log = states.get(id);
		switch(temp_log.getCity())
		{
			case "Delhi" : delhi_temp.add(temp_log.getTemperature()); calcTemp("Delhi", delhi_temp); break;
			case "Mumbai" : mum_temp.add(temp_log.getTemperature()); calcTemp("Mumbai", mum_temp); break;
			case "Srinagar" : sri_temp.add(temp_log.getTemperature()); calcTemp("Srinagar", sri_temp); break;
		}
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
				min=temp;
			if(temp >= max)
				max=temp;
		}
		
		res = min + (max-min)*r.nextDouble();
		System.out.println("Predicted temperature (by TemperaturePredictor2) for " + city + " is: " + res);
	}

}
