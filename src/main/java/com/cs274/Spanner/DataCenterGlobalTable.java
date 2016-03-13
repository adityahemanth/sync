package com.cs274.Spanner;
import java.util.*;


public class DataCenterGlobalTable extends HashMap
{
	private static DataCenterGlobalTable dTable = null;
	private static DataCenterGlobalTable df1Table = null;
	private static DataCenterGlobalTable df2Table = null;
	
	protected ClientGlobalTable() 
	{}

	public static DataCenterGlobalTable getLeaderInstance() {
		if(dTable == null)
			dTable = (DataCenterGlobalTable) new HashMap<String, Queue>();

		return dTable;
	}

	public static DataCenterGlobalTable getfollower1Instance() {
		if(df1Table == null)
			df1Table = (DataCenterGlobalTable) new HashMap<String, Queue>();

		return df1Table;
	}

	public static DataCenterGlobalTable getfollower2Instance() {
		if(df2Table == null)
			df2Table = (DataCenterGlobalTable) new HashMap<String, Queue>();

		return df2Table;
	}

}