package com.tmschedule.member;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import org.bson.Document;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static java.util.Arrays.asList;

import com.tmschedule.database.MongoInstance;

public class Member implements User{
	private String firstName;
	private String lastName;

	// counts for each working position
	private int generalEvaluatorCount = 0;
	private int topicMasterCount = 0;
	private int speechesCount = 0;
	private int evaluatorCount = 0;
	private int grammarianCount = 0;
	private int ahCounterCount = 0;
	private int timerCount = 0;
	
	// what has the member worked recently
	private ArrayList<String> previousRole = new ArrayList<String> ();

	// Constructors
	
	public Member (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		
		getServiceNumbers ();
	}

	// Has this member recently served in a role?
	public boolean recentlyServedInRole (String role) {
		boolean served = false;
		
		if (previousRole.contains(role)) served = true;
		
		return served;
	}
	
	// Add a role to the list of roles this member has worked
	public void addPreviousRole (String role) {
		
		previousRole.add(role);
		
		// We will only look at the previous 3 roles
		if (previousRole.size() > 3) {
			previousRole.remove(0);
		} 
	}
	
	private void getServiceNumbers () {
		// Look for the number of times each member has served as a working member 
		AggregateIterable<Document> iterable;
		
		MongoDatabase db = MongoInstance.getDB();
		
		// Number of times as General Evaluator
		iterable = db.getCollection("schedule").aggregate(asList(
		        new Document("$match", new Document("general evaluator", this.getName())),
		        new Document("$group", new Document("_id", "$general evaluator").append("count", new Document("$sum", 1)))
		        ));
		
		if (iterable.first() != null)
			this.generalEvaluatorCount = iterable.first().getInteger("count");
		
		// Number of times as Topics Master
		iterable = db.getCollection("schedule").aggregate(asList(
		        new Document("$match", new Document("topic master", this.getName())),
		        new Document("$group", new Document("_id", "$id").append("count", new Document("$sum", 1)))
		        ));
		
		if (iterable.first() != null)
			this.topicMasterCount = iterable.first().getInteger("count");
		
		// Number of times as a Speaker
		iterable = db.getCollection("schedule").aggregate(asList(
				new Document("$match", new Document("speaker.name", this.getName())),
				new Document("$group", new Document("_id", "$id").append("count", new Document ("$sum", 1)))
				));

		if (iterable.first() != null)
			this.speechesCount = iterable.first().getInteger("count");
		
		// Number of times as an Evaluator
		iterable = db.getCollection("schedule").aggregate(asList(
				new Document("$match", new Document("evaluator.name", this.getName())),
				new Document("$group", new Document("_id", "$id").append("count", new Document ("$sum", 1)))
				));

		if (iterable.first() != null)
			this.evaluatorCount = iterable.first().getInteger("count");

		// Number of times as a Grammarian
		iterable = db.getCollection("schedule").aggregate(asList(
				new Document("$match", new Document("grammarian", this.getName())),
				new Document("$group", new Document("_id", "$grammarian").append("count", new Document ("$sum", 1)))
				));

		if (iterable.first() != null)
			this.grammarianCount = iterable.first().getInteger("count");

		// Number of times as an Ah Counter
		iterable = db.getCollection("schedule").aggregate(asList(
				new Document("$match", new Document("ah counter", this.getName())),
				new Document("$group", new Document("_id", "$ah counter").append("count", new Document ("$sum", 1)))
				));

		if (iterable.first() != null)
			this.ahCounterCount = iterable.first().getInteger("count");

		// Number of times as a Timer
		iterable = db.getCollection("schedule").aggregate(asList(
				new Document("$match", new Document("timer", this.getName())),
				new Document("$group", new Document("_id", "$timer").append("count", new Document ("$sum", 1)))
				));

		if (iterable.first() != null)
			this.timerCount = iterable.first().getInteger("count");
		
		// Now look at the previous jobs worked by this guy, but we are only going to look at the last 3 weeks		
		LocalDate ld = LocalDate.now().minusWeeks(3);
		Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
				
		// Now, we need to look at the previous working positions
		
		// Check for the roles
		MongoCursor<Document> cursor;  
		MongoCollection<Document> collection = db.getCollection("schedule");
		
		// Toastmaster
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("toastmaster", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("toastmaster");
		
		// general evaluator
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("general evaluator", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("general evaluator");

		// topic master
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("topic master", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("topic master");
		
		// speaker
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("speaker.name", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("speaker");
					
		// evaluator
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("evaluator.name", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("evaluator");
		
		// grammarian
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("grammarian", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("grammarian");
			
		// ah counter
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("ah counter", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("ah counter");
		
		// timer
		cursor = collection.find(
				new Document ("meeting date", new Document ("$gt", date))
				.append("timer", this.getName())
				).iterator();
		
		if (cursor.hasNext()) this.addPreviousRole("timer");
		
		if (this.getName().equals("Edd Dillashaw"))
		System.out.println();
		
	}
	
	// Getters and setters

	@Override 
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getName () {
		return this.getFirstName() + " " + this.getLastName();
	}

	public int getGeneralEvaluatorCount() {
		return generalEvaluatorCount;
	}

	public void setGeneralEvaluatorCount(int generalEvaluatorCount) {
		this.generalEvaluatorCount = generalEvaluatorCount;
	}

	public int getTopicMasterCount() {
		return topicMasterCount;
	}

	public void setTopicMasterCount(int topicMasterCount) {
		this.topicMasterCount = topicMasterCount;
	}

	public int getSpeechesCount() {
		return speechesCount;
	}

	public void setSpeechesCount(int speechesCount) {
		this.speechesCount = speechesCount;
	}

	public int getGrammarianCount() {
		return grammarianCount;
	}

	public void setGrammarianCount(int grammarianCount) {
		this.grammarianCount = grammarianCount;
	}

	public int getAhCounterCount() {
		return ahCounterCount;
	}

	public void setAhCounterCount(int ahCounterCount) {
		this.ahCounterCount = ahCounterCount;
	}

	public int getTimerCount() {
		return timerCount;
	}

	public void setTimerCount(int timerCount) {
		this.timerCount = timerCount;
	}

	public ArrayList<String> getPreviousRole() {
		return previousRole;
	}

	public void setPreviousRole(ArrayList<String> previousRole) {
		this.previousRole = previousRole;
	}

	public int getEvaluatorCount() {
		return evaluatorCount;
	}

	public void setEvaluatorCount(int evaluatorCount) {
		this.evaluatorCount = evaluatorCount;
	}
}
