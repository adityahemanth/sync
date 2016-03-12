package com.cs274.scheduler;

import java.util.*;


public class Operation
{
	String oper; // read or write
	String key;
	long tnxId;

	public String getOper()
	{
		return oper; 
	}

	public String getKey()
	{
		return key;
	}

	public void setKey(String k)
	{
		key = k;
	}

	public void setOper(String o)
	{
		oper = o;
	}
}