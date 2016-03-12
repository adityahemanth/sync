package com.cs274.Spanner;
import java.util.*;

public class ClientTransaction implements Runnable 
{

	ClientConfig config;
	Socket csocket;
	String tid;
	ClientGlobalTable  cTable = ClientGlobalTable.getInstance();

   ClientTransaction(Socket csocket,String txid) 
   {
      this.csocket = csocket;
      this.tid = txid;
   }

   public void run()
   {
   		Operation op;
   		Queue<Operation> transaction;
		transaction = (Queue) lqueue.get(tid);
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
					
				}
				else if(op.oper == "write")
				{

				}
				else if(op.oper == "commit")
				{

				}
				else if(op.oper == "abort")
				{

				}
				else
				{
					system.out.println("Inavlid operation");
					continue;
				}
			}
		}
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