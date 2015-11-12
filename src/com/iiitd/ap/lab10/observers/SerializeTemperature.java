package com.iiitd.ap.lab10.observers;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;

import com.iiitd.ap.lab10.Observer;

public class SerializeTemperature extends Observer {
	@Override
	public void update() {
		long time_now = Calendar.getInstance().get(Calendar.MILLISECOND);
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(""+time_now));
			out.writeObject(this.subject.getState());
			out.close();
			System.out.print("Successfully serialized and stored TemperatureLog Object");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
