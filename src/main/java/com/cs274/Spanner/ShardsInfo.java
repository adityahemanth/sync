package com.cs274.Spanner;
import java.util.*;

public class ShardsInfo extends HashMap
{
	private static ShardsInfo shardList = null;

	ShardsInfo(){}

	public static ShardsInfo getInstance() 
	{
		if( shadList== null)
			shadList = (LockTable) new HashMap<String,String>();

		return table;
	}


}