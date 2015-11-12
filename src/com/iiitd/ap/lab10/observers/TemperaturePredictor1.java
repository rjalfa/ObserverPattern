package com.iiitd.ap.lab10.observers;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;
import com.iiitd.ap.lab10.TemperatureLog;

public class TemperaturePredictor1 extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(5);
	
	private TemperatureLog state;
	
	@Override
	public void update() {
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
		double avg = 0;
		for(Double temp :city_temp)
		{
			avg += temp;
		}
		System.out.println("Predicted temperature (by TemperaturePredictor1) for " + city + " is: " + avg/((double)5));
	}

}
