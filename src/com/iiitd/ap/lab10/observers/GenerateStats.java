package com.iiitd.ap.lab10.observers;

/*
 * @Rounaq Jhunjhunu Wala - 2014089
 * @Shrey Bagroy - 2014099
 */


import java.util.Collections;
import java.util.Vector;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.iiitd.ap.lab10.Observer;

public class GenerateStats extends Observer {
	private CircularFifoQueue<Double> delhi_temp = new CircularFifoQueue<>(100);
	private CircularFifoQueue<Double> mum_temp = new CircularFifoQueue<>(100);
	private CircularFifoQueue<Double> sri_temp = new CircularFifoQueue<>(100);	
	
	private double max;
	private double min;
	private double avg;
	private Vector<Double> sort_median;
	
	@Override
	public synchronized void update(int id) {
		states = this.subject.getStates();
		temp_log = states.get(id);
		switch(temp_log.getCity())
		{
			case "Delhi" : delhi_temp.add(temp_log.getTemperature()); calcStats("Delhi", delhi_temp); break;
			case "Mumbai" : mum_temp.add(temp_log.getTemperature()); calcStats("Mumbai", mum_temp); break;
			case "Srinagar" : sri_temp.add(temp_log.getTemperature()); calcStats("Srinagar", sri_temp); break;
		}
		System.out.println();
	}
	
	public void calcStats(String city, CircularFifoQueue<Double> city_temp)
	{
		max=-999999;
		min=999999;
		avg=0;
		
		sort_median = new Vector<>();
		
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
		if(sort_median.size() == 0) return;
		System.out.println("Stats for " + city + "-");
		System.out.println("Mean temperature: " + avg/((double)city_temp.size()));
		System.out.println("Median temperature: " + calcMedian(sort_median));
		System.out.println("Maximum temperature: " + max);
		System.out.println("Minimun temperature: " + min);
		System.out.println();
		
	}
	
	public static double calcMedian(Vector<Double> list)
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
