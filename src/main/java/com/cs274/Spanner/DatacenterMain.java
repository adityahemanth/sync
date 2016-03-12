package com.cs274.Spanner;
import java.util.*;
import java.net.*;
import java.io.*;

public class DataCenterMain
{
	DBConfig config;
	int paxosLeader = 0;

	
	public static void startFollowerServers(int paxosLeader)
	{
		try
		{
			new Thread(new Follower1(paxosLeader)).start();

			new Thread(new Follower2(paxosLeader)).start();
		}
		catch(Exception e)
		{
			System.out.println("unable to start followers");
		}
	}


	

	public static void main(String args[])
	{
		DataCenterGlobalTable  dTable = DataCenterGlobalTable.getLeaderInstance();
		
		
		//start follower servers
		 startFollowerServers(paxosLeader);
		
		// declare as paxos leader to all followers
		/* Optional in ou setup
		if(0 != declareAsPaxosLeader())
		{
			System.out.println("unable to declare as leader");
			return;
		}
		*/

		//wait for msg from client as paxos leader

		try 
		{
			ServerSocket ss = new ServerSocket(config.paxosLeadersPort[paxosLeader]);

			while(1)
			{
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Operation op = (Operation)ois.readObject();

				if (op!=null)
				{
					// if existing tansaction
					if(dTable.containsKey(op.tnxId))
					{
						Queue<Operation> queue;
						queue = (Queue) dTable.get(op.tnxId);
						queue.add(op);
						//add to the table
						dTable.put(op.tnxId,queue);
					}
					else
					{
						//if new transaction
						queue = new LinkedList<Operation>();
						queue.add(op);
						//add to the table
						dTable.put(op.tnxId,queue);

						new Thread(new LeaderTransaction(s,op.tnxId)).start();
				
					}
					
				}
			}

			
			is.close();
			s.close();
			ss.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

			
	}

} 