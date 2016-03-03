package com.cs274.scheduler; 

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class LogEntry {

	private long LSN = 0;
	private String key;
	private String beforeImage = "";
	private String afterImage = "";

	public LogEntry(String key, String bfrimg, String aftrimg){
		LSN = LSNManager.getLSN();
		key = this.key;
		bfrimg = beforeImage;
		aftrimg = afterImage;
	}

	public String getJSON() {
		Gson gson = new Gson();
		String entry = gson.toJson(this);	 

  		return entry;
	}

}