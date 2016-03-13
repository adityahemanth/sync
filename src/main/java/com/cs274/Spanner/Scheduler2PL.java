package com.cs274.Spanner;
import java.util.*;


public class Scheduler2PL 
{
	private static final int TIMEOUT = 1;


   
	public static LockTable lTable = LockTable.getInstance();
	boolean suspended = false;
	Queue<String> lockedKeys = new LinkedList<String>();
	Timer timer;
	long txnid;


	
	// acquires lock for the given operation key 
	public boolean schedule2PLPhase1(operation op) 
	{
			long lockedtxn = lTable.getLockStatus(op.getKey();	
			// check if the key is not free  AND if the lock is not acquired on the key for same transaction 	
			if( (0 != lockedtxn ) && (op.getTxid() != lockedtxn) ) 
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
          		// aquire a lock	
          		lTable.lockKey(op.getKey(),op.getTxid());
          		//add key to the locked list
          		lockedKeys.add(op.getKey());
          	}
          	else if(0 == lockedtxn)
          	{
          		// aquire a lock	
          		lTable.lockKey(op.getKey(),op.getTxid());
          		//add key to the locked list
          		lockedKeys.add(op.getKey());
          	}

          	// else transaction already has lock on the key
          				

		return 0; //Success

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

	public void setTxid(long tid)
	{
		this.txnid = tid;
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