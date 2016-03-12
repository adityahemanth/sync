package com.cs274.Spanner;
import java.util.*;


public class ClientGlobalTable extends HashMap
{
	private static ClientGlobalTable cTable = null;
	
	protected ClientGlobalTable() 
	{}

	public static ClientGlobalTable getInstance() {
		if(cTable == null)
			cTable = (ClientGlobalTable) new HashMap<String, Queue>();

		return cTable;
	}

	

}