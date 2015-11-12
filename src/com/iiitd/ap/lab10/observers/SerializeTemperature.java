package com.iiitd.ap.lab10.observers;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import com.iiitd.ap.lab10.Observer;
import com.iiitd.ap.lab10.TemperatureLog;

public class SerializeTemperature extends Observer {
	@Override
	public  synchronized void update() {
		long time_now = Calendar.getInstance().get(Calendar.MILLISECOND);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""+time_now));
			for(TemperatureLog i : this.subject.getStates()) out.writeObject(i);
			out.close();
			System.out.println("Successfully serialized and stored TemperatureLog Objects\n--------------------");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
