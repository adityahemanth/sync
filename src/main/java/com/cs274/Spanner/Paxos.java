package com.cs274.Spanner;

import java.lang.Runnable;
import java.util.*;

public class Paxos 
{
	
	
	TPCScheduler TPC;
	Scheduler2PL TPL;
	static ShardInfo shardList = ShardInfo.getInstance();
	int majoity = 2;
	int votes = 0;
	//list of IP addresses of acceptors in the paxos group

	Paxos(Scheduler2PL obj)
	{
		this.TPL = obj;
	}

	public static boolean paxosWrite(Operation op) 
	{
		
	
			if(0 != TPL.schedule2PLPhase1(op))
			{
				// 2pl failure
				return 1;
			}

			if(0 != TPL.dowrite(op))
			{
				return 1;
			}

			if(0 != paxosPhase1())
			{
				//paxos phase 1 failed
				TPC.sendTPCAbortmsg();
				return 1;
			}
			
			if(votes < majoity)
			{
				TPC.sendTPCAbortmsg();
				return 1;
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
	
}