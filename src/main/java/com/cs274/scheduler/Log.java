package com.cs274.scheduler;

public class Log {

	private static String log = null;
	protected Log() {

	}

	public static String getInstance() {
		if(log == null)
			log = "";

		return log;
	}

}



// LSNtransaction	object	modified	before-image	time