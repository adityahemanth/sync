package com.cs274.datastore;


public class Page {

	private String value;
	private long lastupdated;

	public Page() {
		 updateTime();
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value){
		this.value = value;
		updateTime();
	}

	private void updateTime(){

		lastupdated = TimeStamp.getTime();

	}


/*	Structure 

	Meta data - time stamp,

*/

}