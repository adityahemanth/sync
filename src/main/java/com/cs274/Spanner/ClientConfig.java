package com.cs274.Spanner;

import java.util.*;

public class ClientConfig
{

	public String[] paxosLeadersIP = {"127.0.0.0","127.0.0.0","127.0.0.0"};
	public int[] paxosLeadersPort ={1000,1001,1002};
	public String myIP = "127.0.0.0";
	public int myPort = 999;
	public String ycsbIP ="127.0.0.0";
	public int pLeader1MaxKey = 1000;
	public int pLeader2MaxKey = 2000;
	public int pLeader3MaxKey = 3000;
}