package com.cs274.Spanner;
import java.util.*;

public class Shards extends HashMap
{
	private static Shards leaderShard = null;
	private static Shards follower1Shard = null;
	private static Shards follower2Shard = null;

	ShardsInfo(){}

	public static Shards getleaderInstance() 
	{
		if( leaderShard == null)
			leaderShard = (Shards) new HashMap<String,String>();

		return leaderShard;
	}

	public static Shards getfollower1Instance() 
	{
		if( follower1Shard == null)
			follower1Shard = (Shards) new HashMap<String,String>();

		return follower1Shard;
	}

	public static Shards getfollower2Instance() 
	{
		if( follower2Shard == null)
			follower2Shard = (Shards) new HashMap<String,String>();

		return follower2Shard;
	}

}