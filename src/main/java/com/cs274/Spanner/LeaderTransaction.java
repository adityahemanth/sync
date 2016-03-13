package com.cs274.Spanner;
import java.util.*;

public class LeaderTransaction implements Runnable 
{

	DBConfig config;
	Socket csocket;
	String tid;
	//DataCenterGlobalTable  dTable = DataCenterGlobalTable.getLeaderInstance();
   Shards   lshard = Shards.getLeaderInstance();
	Scheduler2PL TPL = new Scheduler2PL();
   Paxos paxos(TPL);
  
	

   ClientTransaction(Socket csocket) 
   {
      this.csocket = csocket;
   }

   
   public void run()
   {
      Operation op;
      boolean failure = 0;
      InputStream is = csocket.getInputStream();
      ObjectInputStream ois = new ObjectInputStream(is);
   

      while(1)
      {
         
            op = (Operation)ois.readObject();
            TPL.setTxid(op.getTxid());

            if(op.getOper() == "read")
            {
               doRead(op);
               OutputStream os = csocket.getOutputStream();
               ObjectOutputStream oos = new ObjectOutputStream(os);

               if(0 != TPL.schedule2PLPhase1(op))
               {
                  // failed to acquire lock
                  op.setValue("null");
               
               }

               op.setValue(lshard.get(op.getKey()));

               // send read value to client                              
               oos.writeObject(op);
            }
            else if(op.getOper() == "write")
            {
               if(0 != paxosWrite(op))
               {
                  failure = 1;
                  //revert all previous writes
               }

            }
            else if(op.getOper() == "commit")
            {
               // no more operations , so wait till transaction is success and send the ack

               if(0 == failure)
               {
                  //send success
               }
               else
               {
                  //send failure
               }
               
            }
            else if(op.getOper() == "commit")
            {
               // release all locks acquired by reads
            }
            
            
         }
      }
   }