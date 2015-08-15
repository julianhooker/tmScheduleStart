package mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoClientProvider {
		
	static private MongoClient mongoClient = new MongoClient("localhost", 27017);
	static MongoDatabase db = mongoClient.getDatabase("julian");
		
	public static MongoCollection<Document> getCollection(){
		MongoCollection<Document> collection = db.getCollection("tmschedule");
		
		return collection;
	}
		
}