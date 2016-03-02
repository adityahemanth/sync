package com.cs274.scheduler;

import java.util.*;

public class LockTable extends HashMap{

	private static LockTable table = null;
	static LockQueue lQueue = LockQueue.getInstance();
	
	protected LockTable() 
	{}
	

	public static LockTable getInstance() {
		if(table == null)
			table = (LockTable) new HashMap<String,Long>();

		return table;
	}

	public static long getLockStatus(String key)
	{
		  // returns transaction id if locked, else returns 0 
		Long ret = (Long)table.get(key);
		return (long)ret.intValue();                     
	}

	public static void lockKey(String key,long tId)
	{
		Long obj = new Long(tId);
		table.put(key,obj);
	}

	public static void enQueueForKey(String key, Scheduler2PL obj)
	{
		lQueue.addToQueue(key,obj);
	}

	public static void unLockKey(String key)
	{
		table.put(key,0);
		Scheduler2PL obj = lQueue.removeFromQueue(key);
		obj.resume();
	}
}