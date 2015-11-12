package com.iiitd.ap.lab10.observers;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import com.iiitd.ap.lab10.Observer;

public class SerializeTemperature extends Observer {
	@Override
	public  synchronized void update(int id) {
		long time_now = Calendar.getInstance().get(Calendar.MILLISECOND);
		states = this.subject.getStates();
		temp_log = states.get(id);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""+time_now));
			out.writeObject(temp_log);
			out.close();
			System.out.println("Successfully serialized and stored TemperatureLog Object for " + city_map.get(id));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
