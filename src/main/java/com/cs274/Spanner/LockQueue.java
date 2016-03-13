package com.cs274.scheduler;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.*;

public class LockQueue extends HashMap
{

	// this is a hashmap singleton pattern
	// has a string for key and queue for the value (BAD ASS!!)
	private static LockQueue lqueue = null;
	
	protected LockQueue() 
	{}

	public static LockQueue getInstance() {
		if(lqueue == null)
			lqueue = (LockQueue) new HashMap<String, PriorityQueue>();

		return lqueue;
	}


	public static void addToQueue(String key, Scheduler2PL obj)
	{

		PriorityQueue<Scheduler2PL> queue;
		if(lqueue.get(key) != null)
		{
			queue = (Queue) lqueue.get(key);
		}
		else
		{
			Comparator<Scheduler2PL> comparator = new PriorityQueueComparator();
			//queue = new LinkedList<Scheduler2PL>();
			queue = new PriorityQueue<Scheduler2PL>(comparator);
		}
		
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