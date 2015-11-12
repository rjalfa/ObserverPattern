package com.iiitd.ap.lab10;

import java.util.Vector;

public abstract class Observer {
	protected TemperatureSensor subject;
	protected Vector<TemperatureLog> states;
	public abstract void update();
}
