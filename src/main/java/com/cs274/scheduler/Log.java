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

	public static void force() {
		// forces the log into stable storage.
	}

	// have to have a before image and an after image
	// log object
}
