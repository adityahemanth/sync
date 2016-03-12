package com.cs274.Spanner;
import java.util.*;


public class Scheduler2PL 
{
	private static final int TIMEOUT = 1;


   
	public static LockTable lTable = LockTable.getInstance();
	Transaction T;
	boolean suspended = false;
	Queue<String> lockedKeys = new LinkedList<String>();
	Timer timer;

	
	public boolean schedule2PLPhase1(Transaction T) 
	{
		//define a object called object
		Operation op;

		// while a queue called transaction is not empty
		while(0 != T.size()) // EPIC !!
		{
			// pop the last one
			op = T.removeOperation();

			// check if it has the lock for the data item
			if(0 != lTable.getLockStatus(op.getKey()))
			{
				// add this scheduler to another queue so that it
				// is resumed when the object is freed.
				timer = new Timer();
				lTable.enQueueForKey(op.getKey(),this);
				try
				{
					// thread will not switch until this block
					// is executed.
					synchronized(this) 
					{
						suspended = true;
            			while(suspended) 
            			{
            				//timer.schedule(new AbortManager(), TIMEOUT*1000);
               				wait();
            			}
          			}
          		}
          		catch(Exception e)
          		{
          			e.printStackTrace();
          			return 1; // Failure 
          		}
          	}

          	// aquire a lock	
          	lTable.lockKey(op.getKey(),T.getId());
          	lockedKeys.add(op.getKey());
			// write / read operation
		}	

		return 0; //Success
		// Notify the 2PC co-ordinator.

		// wait till you get get commit request from 2PC Co ordinator

		

	}


	public boolean schedule2PLProcessingPhase()
	{
		// do all wites
	}

	public boolean scheduler2PLPhase2()
	{

		// Commit Transactions 
		try 
		{
			while(0 != lockedKeys.size())
			{
				lTable.unLockKey(lockedKeys.remove());
			}
		}
		catch(Exception e)
        {
          	e.printStackTrace();
          	return 1; // Failure 
        }

        return 0;
	}

	class AbortManager extends TimerTask {

        @Override
        public void run() {
            // need to handle abort here
            // check log files to 
        }
    }

	
	
    synchronized void resume() 
    {
      suspended = false;
       notify();
    }

}