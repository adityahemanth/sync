package com.cs274.datastore;

import java.io.*;
import java.util.*;



class Database {

	//loadings memcache
	// having only cached objects and no stable storage
	// recovery is done from the log files.

	static Memcache cache = Memcache.getInstance();

	public static String read(String key) {

		String value = cache.getValue(key);
		return value;

		// else {

		// 	String file = "/database/" + key + ".txt";
		// 	//f = new File(file);

		// 	// read file
		// 	// parse JSON with Google Json interpreter (GSON)
		// 	Gson gson = new Gson();
		// 	try 
		// 	{
		// 		BufferedReader br = new BufferedReader(new FileReader(file));  
		// 		Page data = gson.fromJson(br, Page.class);	
		// 		value = data.getValue();
		// 	}

		//     catch(IOException e) 
		// 	{  
	 //   			e.printStackTrace();  
	 //  		}  

	 //  		if(value != null) {
	 //  			cache.put(key, value);
	 //  		}
		// }

		// return value; 

	}

	public static void write(String key, String value) {
		
		cache.put(key,value);

		// String memvalue = (String) cache.get(key);
		// return memvalue;

		// // if in cache
		// if(memvalue != null ) {
		// 	cache.updateObject(key,value); 
		// }

		// // else
		// else {

		// 	cache.updateObject(key,value);
		// 	String file = "/database/" + key + ".txt";
		// 	//f = new File(file);

		// 	// read file
		// 	// parse JSON with Google Json interpreter (GSON)
		// 	Gson gson = new Gson();
		// 	try 
		// 	{
		// 		BufferedReader br = new BufferedReader(new FileReader(file));  
		// 		Page data = gson.fromJson(br, Page.class);	
		// 		//cache.addNewObject(key,data.getValue()); // read from file  
		// 		//cache.updateObject(key,value);           // update new value in memcache, not yet in file 
		// 	}
		//     catch(IOException e)
		// 	{  
	 //   			e.printStackTrace();  
	 //  		}  

		// }			
		
		// return;
		// // 
	}

	public static void flush(ArrayList<String> keys){
		//gets a list of keys
		//write 
	}

}


