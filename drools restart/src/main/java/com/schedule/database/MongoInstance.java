package com.schedule.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoInstance {
	
	private static MongoClient mongoClient = new MongoClient("localhost", 27017);
	private static MongoDatabase db = mongoClient.getDatabase("julian");

	private static MongoInstance instance = new MongoInstance(); 
	
	private MongoInstance() {}
	
	public static MongoInstance getInstance() {
		return instance;
	}
	
	public static MongoDatabase getDB() {
		return db; 
	}
}
