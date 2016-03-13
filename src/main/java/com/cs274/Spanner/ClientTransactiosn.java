package com.cs274.Spanner;
import java.util.*;

public class ClientTransaction implements Runnable 
{

	ClientConfig config;
	Socket csocket;
	String tid;
	ClientGlobalTable  cTable = ClientGlobalTable.getInstance();
	Queue<Operation> shardwrites[3];
	Socket lsocket[3] = { null, null, null};
	
	

   ClientTransaction(Socket csocket,String txid) 
   {
      this.csocket = csocket;
      this.tid = txid;
   }

   public static getLeadersocket(int destPLeader)
   {
   		if(null == lsocket[destPLeader] )
   		{
   			lsocket[destPLeader] = new Socket(paxosLeadersIP[count],paxosLeadersPost[count]);	
   		}

   		return lsocket[destPLeader];
   }	

   public void run()
   {
   		Operation op;
   		Queue<Operation> transaction;
		transaction = (Queue) cTable.get(tid);
		int destPLeader;

		while(1)
		{
			while(0 != transaction.size())
			{
				op = transaction.remove();
				destPLeader = getDestPaxosLeader(op.getKey());

				if(-1 == destPLeader)
				{
					continue;
				}

				if(op.getOper() == "read")
				{
					OutputStream os = csocket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);

					// send read to paxos leader and get the value
					Operation opR = readFromShards(destPLeader, op);

					// write back the value to ycsb
					oos.writeObject(opR);
				}
				else if(op.getOper() == "write")
				{
					shardwrites[destPLeader].add(op);
				}
				else if(op.getOper() == "commit")
				{
					if(0 != shardwrites[0].size())
					{
						sendWriteToShad(0);
					}
					if(0 != shardwrites[1].size())
					{
						sendWriteToShad(1);
					}
					if(0 != shardwrites[2].size())
					{
						sendWriteToShad(2);
					}

					continue;				

				}
				else if(op.getOper() == "abort")
				{
					sendAbortToAllShads();
					cTable.remove(tid);
					return;
				}
				else
				{
					system.out.println("Inavlid operation");
					continue;
				}
			}
		}
   }



   public static String sendAbortToAllShads()
   {
   		int count = 0;
   		int all = 3;
   		Operation op;

   		try
   		{
   			while(count < all)
   			{
   				//Socket s = new Socket(paxosLeadersIP[count],paxosLeadersPost[count]);
   				Socket s = getLeadersocket(count);
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				op.setOper("abort");
				oos.writeObject(op);
   			}
   		}
   		catch(Exception e)
		{
			System.out.println(e);
		}

   }

   public static void sendWriteToShad(int destPLeader)
   {
   		try
		{
			//Socket s = new Socket(paxosLeadersIP[destPLeader],paxosLeadersPost[paxosLeadersPost]);
			Socket s = getLeadersocket(destPLeader);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Operation op;
			while(0 != shardwrites[destPLeader].size())
			{
				op = shardwrites[destPLeader].remove();
				oos.writeObject(op);
			}

			op.setOper("commit");
			oos.writeObject(op);

			// get response from paxos leader
			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			op = (Operation)ois.readObject();

			//send response back to client
			OutputStream os = csocket.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oss.writeObject(ack);

			cTable.remove(tid);
			return;
			

			
			oos.close();
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return ack;
   }

	public static Operation readFromShards(int destPLeader, Operation op)
	{
		try
		{
			//Socket s = new Socket(paxosLeadersIP[destPLeader],paxosLeadersPost[paxosLeadersPost]);
			Socket s = getLeadersocket(destPLeader);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(op);

			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			Operation opR = (Operation)ois.readObject();

			oos.close();
			os.close();
			s.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

		return opR;
	}


	public int getDestPaxosLeader(String k)
	{
		int destPLeader = -1;
		int key = Integer.parseInt(k);

		if(key < pLeader1MaxKey)
		{
			destPLeader = 0;
		}
		else if (key < pLeader2MaxKey)
		{
			destPLeader = 1;
		}
		else if (key < pLeader3MaxKey)
		{
			destPLeader = 2;
		}
		else
		{
			system.out.println("Invalid key");
		}

		return destPLeader;
	}

}
