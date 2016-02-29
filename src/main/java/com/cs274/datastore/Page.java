package com.cs274.datastore;


public class Page {

	private String value;
	private long lastupdated;
	public Page() {
		lastupdated = TimeStamp.getTime(); 
	}

	public String getValue()
	{
		return value;
	}
/*	Structure 

	Meta data - time stamp,

*/

}