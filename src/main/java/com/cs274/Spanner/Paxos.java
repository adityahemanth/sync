package com.cs274.Spanner;

import java.lang.Runnable;
import java.util.*;

public class Paxos implements Runnable {
	
	private Thread t;
	Transaction T;
	boolean startPhase = 0;
	Scheduler2PL TPL;
	TPCScheduler TPC;
	static ShardInfo shardList = ShardInfo.getInstance();
	int majoity = 2;
	int votes = 0;
	//list of IP addresses of acceptors in the paxos group

	Paxos(Transaction trans)
	{
		T = trans;
	}


// at the start of the instance
	Paxos(String start)
	{
		if(start == "start")
		{
			startPhase = 1;
		}	
		//if leader
		//send prepare

		//else
		//excpect prepare and send promise

	}
	

//-------------only at start------------------------------
	//Paxos Leader's
	void sendPrepare()  
	{
		//send it to all cohorts declaring you are the leader
	}

	//Paxos Acceptors'
	void sendPromise()
	{
		//send as reply to prepare stating the acceptance of leadership
	}



//-------------For all transactions----------------------------


	public void run() 
	{
		if(startPhase = 1)
		{
			//send prepare msg to all followers
			return;
		}
		else
		{
			
			if(0 != TPL.schedule2PLPhase1(T))
			{
				// 2pl failure
				return;
			}

			if(0 != TPL.dowrites(T))

			if(0 != paxosPhase1())
			{
				//paxos phase 1 failed
				TPC.sendTPCAbortmsg();
				return;
			}
			
			if(votes < majoity)
			{
				TPC.sendTPCAbortmsg();
				return;
			}
			else
			{
				votes = 0;
				TPC.sendTPCcommitmsg();
			}
			
			
			
			waitForesponseFromTPCCoordinator();

			if(0 != TPL.schedule2PLPhase2())
			{
				//2pl failed
				return;
			}	
			if( 0 != paxosPhase2())
			{
				sendCommitFailueResponse();
				revetChanges();
				return;
			}
			
			if(votes < majoity)
			{
				sendCommitFailueResponse();
				revetChanges();
				return;
			}
			else
			{
				votes = 0;
				sendCommitSuccessResponse();
			}

			if(0 != waitForAckFromTPCCoordinator())
			{
				revetChanges();
				return;
			}

			sendCommitAcksToAllAndGetResponse();

			
		}

	}

	
	public static boolen paxosPhase1()
	{
		if(0 != sendAcceptRequestAndGetesponses())
		{
			// sending accept request failed
			return 0;
		}	

				
		
		return 1;
		

	}

	//Paxos Leaders'
	public static boolean sendAcceptRequest()
	{

	}

	//Paxos Acceptors'
	public static boolean sendAccept()
	{

	}

	public void start ()
    {
      if (t == null)
      {
         t = new Thread (this, "");
         t.start ();
      }
    }


}