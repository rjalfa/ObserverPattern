package com.iiitd.ap.lab10.observers;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;
import com.iiitd.ap.lab10.TemperatureLog;

public class GenerateStats extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(100);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(100);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(100);	
	
	private TemperatureLog state;
	private double max;
	private double min;
	private double avg;
	private ArrayList<Double> sort_median;
	
	@Override
	public void update() {
		state = this.subject.getState();
		switch(state.getCity())
		{
			case "Delhi" : delhi_temp.add(state.getTemperature()); break;
			case "Mumbai" : mum_temp.add(state.getTemperature()); break;
			case "Srinagar" : sri_temp.add(state.getTemperature()); break;
		}
		calcStats("Delhi", delhi_temp); 
		calcStats("Mumbai", mum_temp); 
		calcStats("Srinagar", sri_temp); 
	}
	
	public void calcStats(String city, CircularFifoQueue<Double> city_temp)
	{
		max=-999999;
		min=999999;
		avg=0;
		
		sort_median = new ArrayList<>();
		
		for(Double temp: city_temp)
		{
			if(temp <= min)
				min=temp;
			if(temp >= max)
				max=temp;
			avg+=temp;
			sort_median.add(temp);
		}
		
		Collections.sort(sort_median);
		
		System.out.println("Stats for " + city);
		System.out.println("Mean temperature: " + avg);
		System.out.println("Median temperature: " + calcMedian(sort_median));
		System.out.println("Maximum temperature: " + max);
		System.out.println("Minimun temperature: " + min);
		System.out.println("---------------");
		System.out.println();
		
	}
	
	public static double calcMedian(ArrayList<Double> list)
	{
		int middle = list.size()/2;
		if(list.size()%2 == 1){
			return list.get(middle);
		}
		else
		{
			return (list.get(middle) + list.get(middle -1))/(double)2;
		}
	}
	
}
