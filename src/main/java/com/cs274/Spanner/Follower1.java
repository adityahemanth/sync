package com.cs274.Spanner;
import java.util.*;

public class Follower1 implements Runnable 
{
	int paxosLeader;
	DBConfig config;

	Follower1(int pl)
	{
		this.paxosLeader = pl;
	}

	public void run()
   	{
   		int follower = 0; 
		DataCenterGlobalTable  df1Table = DataCenterGlobalTable.getfollower1Instance();

		try 
		{
			ServerSocket ss = new ServerSocket(config.paxosFollowePort[paxosLeader][follower]);

			while(1)
			{
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Operation op = (Operation)ois.readObject();

				if (op!=null)
				{
					// if existing tansaction
					if(df1Table.containsKey(op.tnxId))
					{
						Queue<Operation> queue;
						queue = (Queue) df1Table.get(op.tnxId);
						queue.add(op);
						//add to the table
						df1Table.put(op.tnxId,queue);
					}
					else
					{
						//if new transaction
						queue = new LinkedList<Operation>();
						queue.add(op);
						//add to the table
						df1Table.put(op.tnxId,queue);

						new Thread(new Follower1Transaction(s,op.tnxId)).start();
				
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