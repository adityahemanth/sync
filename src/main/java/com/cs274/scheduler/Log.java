package com.cs274.scheduler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Log {

	private static String log = null;
	private static Gson gson = new Gson();
	PrintWriter writer = new PrintWriter("log.txt", "UTF-8");

	protected Log() {

	}

	public static String getInstance() {
		if(log == null)
			log = "";

		return log;
	}

	public static void force() {
		writer.print(log);
		log = null;
	}

	public static void add(LogEntry entry){ 
		log = log + "\n" + gson.toJson(entry);
	}

	// have to have a before image and an after image
	// log object
}
