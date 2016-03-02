package com.cs274.scheduler;

import java.util.*;
import com.cs274.datastore.TimeStamp;  

public class Transaction 
{
	private static long id = TimeStamp.getTime();
	private static Queue<Operation> T = new LinkedList<Operation>();	


	public static void addOpeation(Operation op)
	{
		T.add(op);
	}

	public static Operation removeOperation()
	{
		return T.remove(); 
	}

	public static int size()
	{
		return T.size(); 
	}

	public static long getId(){
		return id;
	}
}