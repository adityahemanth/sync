package com.cs274.Spanner;
import java.util.*;

public class ClientTransaction implements Runnable 
{

	ClientConfig config;
	Socket csocket;
	String tid;
	ClientGlobalTable  cTable = ClientGlobalTable.getInstance();
	Queue<Operation> shardwrites[3];
	

   ClientTransaction(Socket csocket,String txid) 
   {
      this.csocket = csocket;
      this.tid = txid;
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
				destPLeader = getDestPaxosLeader(op.key);

				if(-1 == destPLeader)
				{
					continue;
				}

				if(op.oper == "read")
				{
					OutputStream os = csocket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);

					// send read to paxos leader and get the value
					Operation opR = readFromShards(destPLeader, op);

					// write back the value to ycsb
					oos.writeObject(opR);
				}
				else if(op.oper == "write")
				{
					shardwrites[destPLeader].add(op);
				}
				else if(op.oper == "commit")
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
				else if(op.oper == "abort")
				{
					sendAbortToAllShads();
					cTable.remove(tid);
					return;
				}
				else if(op.oper == "success" || op.oper == "failure")
				{
					OutputStream os = csocket.getOutputStream();
					ObjectOutputStream oos = new ObjectOutputStream(os);
					oss.writeObject(ack);

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
   		try
   		{
   			while(count < all)
   			{
   				Socket s = new Socket(paxosLeadersIP[count],paxosLeadersPost[count]);
				OutputStream os = s.getOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(os);
				
				oos.writeObject("abort");
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
			Socket s = new Socket(paxosLeadersIP[destPLeader],paxosLeadersPost[paxosLeadersPost]);
			OutputStream os = s.getOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(os);
			Operation op;
			while(0 != shardwrites[destPLeader].size())
			{
				op = shardwrites[destPLeader].remove();
				oos.writeObject(op);
			}
			oos.writeObject("commit");

			InputStream is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			String ack = (Operation)ois.readObject();

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
			Socket s = new Socket(paxosLeadersIP[destPLeader],paxosLeadersPost[paxosLeadersPost]);
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
