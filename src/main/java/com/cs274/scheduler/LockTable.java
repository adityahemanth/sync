package com.cs274.scheduler;

import java.util.*;

public class LockTable extends HashMap{

	private static LockTable table1 = null;
	private static LockTable table2 = null;
	private static LockTable table3 = null;

	protected LockTable() {

	}

	public static LockTable getInstanceShard1() {
		if(table1 == null)
			table1 = (LockTable) new HashMap<String, String>();

		return table1;
	}

	public static LockTable getInstanceShard2() {
		if(table2 == null)
			table2 = (LockTable) new HashMap<String, String>();

		return table2;
	}

	public static LockTable getInstanceShard3() {
		if(table3 == null)
			table3 = (LockTable) new HashMap<String, String>();

		return table3;
	}
}