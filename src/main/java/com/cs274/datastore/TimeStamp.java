package com.cs274.datastore;

public class TimeStamp {
	private static long time = 0;

	protected TimeStamp() {}

	public static long getTime()
	{
		time += 1;
		return time; 
	}


}