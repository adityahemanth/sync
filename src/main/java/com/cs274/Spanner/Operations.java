package com.cs274.Spanner;

import java.util.*;


public class Operation implements Serializable
{
	long tnxId;
	String oper; // read or write
	String key;
	String value;

	public Operation(long tid, ,String op, String k)
	{
		this.oper = op;
		this.key = k;
		this.tnxid = tid;
	}

	public Operation(long tid, ,String op, String k, String v)
	{
		this.oper = op;
		this.key = k;
		this.tnxid = tid;
		this.value = v;
	}

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