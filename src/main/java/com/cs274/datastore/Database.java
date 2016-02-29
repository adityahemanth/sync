package com.cs274.datastore;

import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


class Database {

	private static InputStream readFile;
	private static OutputStream writeFile;
	private static File f;

	//loadings memcache
	static Memcache cache = Memcache.getInstance();

	public static String read(String key) 
	{

		String value = (String) cache.get(key);

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
		String file = "/database" + key + ".txt";
		// 
	}

}


