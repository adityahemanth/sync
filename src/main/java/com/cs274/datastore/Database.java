package com.cs274.datastore;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


class Database {

	//loadings memcache
	static Memcache cache = Memcache.getInstance();

	public static String read(String key) {

		String value = cache.getValue(key);

		if(value != null )
			return value;

		else {

			String file = "/database/" + key + ".txt";
			//f = new File(file);

			// read file
			// parse JSON with Google Json interpreter (GSON)
			Gson gson = new Gson();
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader(file));  
				Page data = gson.fromJson(br, Page.class);	
				value = data.getValue();
			}

		    catch(IOException e) 
			{  
	   			e.printStackTrace();  
	  		}  

	  		if(value != null) {
	  			cache.put(key, value);
	  		}
		}

		return value; 
	}

	public static void write(String key, String value) {
		
		String memvalue = (String) cache.get(key);
		if(memvalue != null )  // if in memcache 
		{
			cache.updateObject(key,value); 
		}

		else     // if not in mem cache, get to memcache
		{
			
			cache.updateObject(key,value);
			String file = "/database/" + key + ".txt";
			//f = new File(file);

			// read file
			// parse JSON with Google Json interpreter (GSON)
			Gson gson = new Gson();
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader(file));  
				Page data = gson.fromJson(br, Page.class);	
				//cache.addNewObject(key,data.getValue()); // read from file  
				//cache.updateObject(key,value);           // update new value in memcache, not yet in file 
			}
		    catch(IOException e)
			{  
	   			e.printStackTrace();  
	  		}  

		}			
		
		return;
		// 
	}

	public static void flush(ArrayList<String> keys){
		//gets a list of keys
		//write 
	}

}


