package com.cs274.scheduler;

import java.util.*;

public class LockTable extends HashMap{

	private static LockTable table = null;
	protected LockTable() {

	}

	public static LockTable getInstance() {
		if(table == null)
			table = new Hashmap<String, String>();

		return table;
	}
}