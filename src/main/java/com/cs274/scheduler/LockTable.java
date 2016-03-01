package com.cs274.scheduler;

import java.util.*;

public class LockTable extends HashMap{

	private static LockTable table = null;
	
	protected LockTable() 

	}

	public static LockTable getInstanceShard1() {
		if(table == null)
			table = (LockTable) new HashMap<String, String>();

		return table;
	}

	
}