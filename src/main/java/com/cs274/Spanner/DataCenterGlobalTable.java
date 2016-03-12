package com.cs274.Spanner;
import java.util.*;


public class DataCenterGlobalTable extends HashMap
{
	private static DataCenterGlobalTable dTable = null;
	
	protected ClientGlobalTable() 
	{}

	public static DataCenterGlobalTable getInstance() {
		if(dTable == null)
			dTable = (DataCenterGlobalTable) new HashMap<String, Queue>();

		return dTable;
	}

	

}