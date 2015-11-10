package com.iiitd.ap.lab10;

public abstract class Observer {
	protected TemperatureSensor subject;
	public abstract void update();
}
