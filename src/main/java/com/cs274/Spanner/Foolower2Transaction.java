package com.cs274.Spanner;
import java.util.*;

public class Follower1Transaction implements Runnable 
{

	DBConfig config;
	Socket csocket;
	String tid;
	//DataCenterGlobalTable  df1Table = DataCenterGlobalTable.getInstance();

	

   ClientTransaction(Socket csocket) 
   {
      this.csocket = csocket;
   }

   public void run()
   {