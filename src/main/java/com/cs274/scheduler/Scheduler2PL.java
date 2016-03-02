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

	Scheduler2PL(Transaction trans)
	{
		T = trans;
	}

	public void run() 
	{
		//define a object called object
		Operation op;

		// while a queue called transaction is not empty
		while(0 != T.size())
		{
			// pop the last one
			op = T.removeOperation();

			// check if it has the lock for the data item
			if(0 != lTable.getLockStatus(op.getKey()))
			{
				// add this scheduler to another queue so that it
				// is resumed when the object is freed.
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

          	// aquire a lock	
          	lTable.lockKey(op.getKey(),T.getId());
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