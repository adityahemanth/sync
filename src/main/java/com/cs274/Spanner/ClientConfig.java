package com.cs274.Spanner;

import java.util.*;

public class ClientConfig
{

	String[] paxosLeadersIP = {"127.0.0.0","127.0.0.0","127.0.0.0"};
	int[] paxosLeadersPost ={1000,1001,1002};
	String myIP = "127.0.0.0";
	int myPort = 999;
	int coOrdinator = 1;
	int pLeader1MaxKey = 1000;
	int pLeader2MaxKey = 2000;
	int pLeader3MaxKey = 3000;
}