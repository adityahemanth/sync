package com.cs274.datastore;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


class Database {

	//private static InputStream readFile;
	//private static OutputStream writeFile;
	//private static File f;

	//loadings memcache
	static Memcache cache = Memcache.getInstance();

	public static String read(String key) 
	{

		String value = cache.getValue(key);

		if(value != null )
			return value;

		else 
		{

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
		}			
		return value; 
	}

	public static void write(String key, String value) {
		
		String memvalue = (String) cache.get(key);
		if(memvalue != null )  // if in memcache 
		{
			cache.updateObject(key,value); 
		}

		if(memvalue == null)      // if not in mem cache, get to memcache
		{

			String file = "/database/" + key + ".txt";
			//f = new File(file);

			// read file
			// parse JSON with Google Json interpreter (GSON)
			Gson gson = new Gson();
			try 
			{
				BufferedReader br = new BufferedReader(new FileReader(file));  
				Page data = gson.fromJson(br, Page.class);	
				cache.addNewObject(key,data.getValue()); // read from file  
				cache.updateObject(key,value);           // update new value in memcache, not yet in file 
			}
		    catch(IOException e) 
			{  
	   			e.printStackTrace();  
	  		}  
		}			
		
		return;
		// 
	}




}


