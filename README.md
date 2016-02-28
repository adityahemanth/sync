TODO

// each file has one object
//sched layer
//	- Memory management { 10 MB }

//		each time you read, keep it in memory.
//		mem = mem - objmem;
//		log force; flush page, read new.

//		keep track, then remove when full. 

//	- read table ( key - null)
//	- update table ( key - T1 )
//	- read / write
//  - commit / abort
//  - update table ( key - null)


In memory
* LOCK TABLE
* MAP - key to file
* LOG - String


Disk
* Files ( each object has a file)
* LOG (of all the transactions)



Architecture :
	How to make it concurrent ? Threads. What handles what ?
	- concurrent transactions (data item operations)
		Each thread opens/ reads/ writes files.


//////////////////////////////////////////////////////*
			MONGO DB


	Key - value (object) ( memory / disk ? ( No corruption ))
	Logs ? - where are they stored ?
	what if it is corrupted. 

