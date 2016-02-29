package com.cs274.datastore;

import java.util.*;

public class Memcache extends HashMap{

	private static Memcache cache = null;
	private int maxsize = 10000;

	protected Memcache() {}

	public static Memcache getInstance(){
		if(cache == null)
			cache = new HashMap<String, String>();

		return cache;
	}

	//TODO
	// override put and get to enable flushing
	
}