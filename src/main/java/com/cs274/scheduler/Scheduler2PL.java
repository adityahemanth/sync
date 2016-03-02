package com.cs274.scheduler;

import java.lang.Runnable;
import java.util.*;

public class Scheduler2PL implements Runnable 
{
   	private Thread t;
	public static LockTable lTable = LockTable.getInstance();
	Transaction T;
	boolean suspended = false;
	Queue<String> lockedKeys = new LinkedList<String>();

	Scheduler2PL(Transaction t)
	{
		T = t;
	}

	public void run() 
	{
		Operation op;
		while(0 != T.size())
		{
			op = T.removeOperation();
			if(0 != lTable.getLockStatus(op.getKey()))
			{
				lTable.enQueueForKey(op.getKey(),this);
				try
				{

					synchronized(this) 
					{
						suspended = true;
            			while(suspended) 
            			{
               				wait();
            			}
          			}
          		}
          		catch(InterruptedException e)
          		{
          			e.printStackTrace();
          		}
          	}	
          	lTable.lockKey(op.getKey(),T.id);
          	lockedKeys.add(op.getKey());
			// write / read operation
		}	

		// Notify the 2PC co-ordinator.

		// wait till you get get commit request from 2PC Co ordinator

		while(0 != lockedKeys.size())
		{
			lTable.unLockKey(lockedKeys.remove());
		}

	}

	
	public void start ()
    {
      if (t == null)
      {
         t = new Thread (this, "");
         t.start ();
      }
    }

    synchronized void resume() 
    {
      suspended = false;
       notify();
    }


}