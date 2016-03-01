package com.cs274.datastore;

import java.util.*;

public class Memcache extends HashMap{

	// have a parallel thread that flushes data items.??
	//	trigger it only when 750 + 
	//  500 stop

	private static Memcache cache = null;
	private static int maxsize = 1000;
	private static int count = 0;

	protected Memcache() {}

	public static Memcache getInstance(){
		if(cache == null)
			cache = (Memcache) new HashMap<String, String>();

		return cache;
	}

	public static void addNewObject(String key, String value)
	{
		if(count < maxsize)
		{
			cache.put(key,value);
			count++;
		}
		else
		{
			// divy said just keep everything in memory. 
			// best thing to do, write to file in parallel and also keep the object.
		}
	}

	public static void removeObject(String key)
	{
		cache.remove(key);
		count--;
	}
	public static void updateObject(String key, String value)
	{
		cache.put(key,value);
	}

	public static void removeObject(String key){
		//removes the passed key
		//decrements counter
	}

	public static String getValue(String key)
	{
		return (String)cache.get(key);
	}

	public static int getCount(){
		return count;
	}

	//TODO
	// override put and get to enable flushing
	
}