package com.cs274.Spannerpaxos;
package com.cs274.scheduler;

import java.lang.Runnable;
import java.util.*;

public class Paxos implements Runnable {
	
	private Thread t;
	Transaction T;
	//list of IP addresses of acceptors in the paxos group

	Paxos(Transaction trans)
	{
		T = trans;
	}

	Paxos(String start)
	{
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

	}

	//Paxos Leaders' for accepting transaction from client
	void pushTransactiontoPaxos()
	{
		// Input : operations relating the shard its leading
		// To do :  
	}

	//Paxos to add the transactions of that shard to 2PL
	void pushTransactionto2Pl()
	{

	}

	//Paxos Leaders'
	void sendAcceptRequest()
	{

	}

	//Paxos Acceptors'
	void sendAccept()
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