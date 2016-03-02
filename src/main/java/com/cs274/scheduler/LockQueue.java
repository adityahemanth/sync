package com.cs274.scheduler;

import java.util.*;

public class LockQueue extends HashMap
{

	private static LockQueue lqueue = null;
	
	protected LockQueue() 
	{}

	public static LockQueue getInstance() {
		if(lqueue == null)
			lqueue = (LockQueue) new HashMap<String, Queue>();

		return lqueue;
	}

	public static void addToQueue(String key, Scheduler2PL obj)
	{
		Queue<Scheduler2PL> queue = new LinkedList<Scheduler2PL>();
		queue = (Queue)lqueue.get(key); 
		
		queue.add(obj);
		lqueue.put(key,queue);
	}

	public static Scheduler2PL removeFromQueue(String key)
	{
		Queue queue;
		Scheduler2PL obj = null;

		queue = (Queue) lqueue.get(key);

		
		if(0 != queue.size())
		{
			obj = (Scheduler2PL)queue.remove();
		}
	
		lqueue.put(key,queue);	
		
		return obj;
	}

}