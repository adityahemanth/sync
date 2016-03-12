package com.cs274.Spanner;

import java.util.*;

public class ClientConfig
{

	public String[] paxosLeadersIP = {"127.0.0.0","127.0.0.0","127.0.0.0"};
	public int[] paxosLeadersPort = {1000,1001,1002};  //x,y,z
	public int[][] followerPots = {
		  					{1003,1004},    //y,z
	                        {1005,1006},	//x,z
	                        {1007,1008}		//x,y
	                    };
	
}