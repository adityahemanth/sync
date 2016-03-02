package com.cs274.scheduler;

import java.util.*;
import com.cs274.datastore.TimeStamp;  

public class Transaction 
{
	long id = TimeStamp.getTime();
	Queue<Operation> T = new LinkedList<Operation>();	


	public void addOpeation(Operation op)
	{
		T.add(op);
	}

	public Operation removeOperation()
	{
		return T.remove();
	}

	public int size()
	{
		return T.size(); 
	}
}