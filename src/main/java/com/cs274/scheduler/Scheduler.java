package com.cs274.scheduler;




// 2PL -> in a shard
// 2PC -> among shards
// Paxos -> Data centers


// what is a shard ?
// Architure

// Client -> DBClient -> Paxos + 2PClayer -> 2PLlayer -> Database
// we don't know what the transaction needs in advance. Prevent deadlocks.
// dectect deadlock cycles. 

// altruistic locking


// gets a request
//		- read
//		- write

// read request
//		1) make a new thread
//		2) read lock-table
//		3) wait if it is already locked
//		4) aquire lock if not locked
//		5) create a DB object.
//		6) write to log
//		7) read the result.

// write request
//		1) make a new thread
//		2) read lock-table
//		3) wait if it is already locked
//		4) aquire lock if not locked
//		5) create a DB object.
//		6) write to log
//		7) write value the result.

// we need table for each transaction.


/*
public class User{

	private String name;
	private int age;
	private String city;

	public User {
	
	}
}

// 8KB files.
// read -> free. write incurs lock wait. 


User u = new User("Hemanth", 22, "Goleta");

T1 ( key, user, age : 23)
T1 ( key, college: UCSB)

Entity e = new Entity("User",key);
e.setProperty("height",23);
e.setProperty("color", yellow);

datastore.put(e);

// make your own benchmark.

// T -> thread. 
// t -> number.
// rand(op) (1 - 10)
// 1 - 10 ( if 10 abort)
// commit.

// benchmark 
//		array (String keys);

// TODO
1) write to and read from files

*/


// commit request
//		1) 
//		2) 


// abort request
//		1) read log file from the bottom. (how do you parse this)

