package com.cs274.Spanner;

import java.util.*;
import java.net.*;
import java.io.*;


public class Client
{
	

	public static void main(String args[])
	{

		ClientGlobalTable  cTable = ClientGlobalTable.getInstance();
		ClientConfig config;
		try 
		{
			ServerSocket ss = new ServerSocket(config.myPort);
			while(1)
			{
				Socket s = ss.accept();
				InputStream is = s.getInputStream();
				ObjectInputStream ois = new ObjectInputStream(is);
				Operation op = (Operation)ois.readObject();

				if (op!=null)
				{
					// if existing tansaction
					if(cTable.containsKey(op.tnxId))
					{
						Queue<Operation> queue;
						queue = (Queue) cTable.get(op.tnxId);
						queue.add(op);
						//add to the table
						cTable.put(op.tnxId,queue);
					}
					else
					{
						//if new transaction
						queue = new LinkedList<Operation>();
						queue.add(op);
						//add to the table
						cTable.put(op.tnxId,queue);

						new Thread(new ClientTransaction(s,op.tnxId)).start();
				
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