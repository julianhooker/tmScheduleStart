package com.tmschedule.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

// Singleton class for the Mongo database connection
public class MongoInstance {
	private static MongoClient mongoClient = new MongoClient("localhost", 27017);
	private static MongoDatabase db = mongoClient.getDatabase("tmschedule");
	
	public static MongoDatabase getDB() {
		return db; 
	}
}
