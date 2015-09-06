package com.tmschedule.member;

import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.client.MongoCursor;
import com.tmschedule.database.MongoInstance;

public final class MemberUtils {
	// Get all active members
	public static ArrayList<User> getActiveMembers() {
		ArrayList<User> memberList = new ArrayList<User>();		
		
		MongoCursor<Document> cursor = MongoInstance.getDB().getCollection("members")
				.find(new Document ("active", "true")).iterator();
		try {
		    while (cursor.hasNext()) {
		        Document doc = cursor.next();
		        
		        memberList.add(UserFactory.getMember(
		        		doc.getString("first name"), 
		        		doc.getString("last name")));
		    }
		} finally {
		    cursor.close();
		}
		
		return memberList;
	}
	
	// Get all members
	public static ArrayList<Member> getMembers() {
		ArrayList<Member> memberList = new ArrayList<Member>();		
		
		MongoCursor<Document> cursor = MongoInstance.getDB().getCollection("members")
				.find().iterator();
		try {
		    while (cursor.hasNext()) {
		        Document doc = cursor.next();
		        
		        memberList.add(UserFactory.getMember(
		        		doc.getString("first name"), 
		        		doc.getString("last name")));
		    }
		} finally {
		    cursor.close();
		}
		
		return memberList;
	}
	
}
