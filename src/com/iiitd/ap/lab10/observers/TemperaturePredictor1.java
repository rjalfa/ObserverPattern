package com.iiitd.ap.lab10.observers;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;

public class TemperaturePredictor1 extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(5);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(5);
	
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
		double avg = 0;
		for(Double temp :city_temp)
		{
			avg += temp;
		}
		System.out.println("Predicted temperature (by TemperaturePredictor1) for " + city + " is: " + avg/((double)city_temp.size()));
	}
}
