package com.cs274.scheduler;

public class LockTable {

	private static LockTable table = null;
	protected LockTable() {

		// TODO
		// make a hashmap.
	}

	public static LockTable getInstance() {
		if(table == null)
			table = new LockTable();

		return table;
	}
}