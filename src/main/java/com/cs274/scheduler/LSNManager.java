package com.cs274.scheduler; 

public class LSNManager {
	
	private static long lsn = 0;
	private LSNManager(){}

	public static long getLSN() {
		return lsn+1;
	}
}