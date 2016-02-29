package com.cs274.datastore;

public class Log {

	private static String log = null;
	protected Log() {

	}

	public static String getInstance() {
		if(log == null)
			log = new String;

		return log;
	}

}