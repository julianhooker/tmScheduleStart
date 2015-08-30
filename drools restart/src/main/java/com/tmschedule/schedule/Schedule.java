package com.tmschedule.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Arrays.asList;

import org.bson.Document;

import com.mongodb.client.MongoDatabase;
import com.tmschedule.database.MongoInstance;
import com.tmschedule.member.Member;

public class Schedule {
	private Date meetingDate;
	private Member toastmaster;
	private Member generalEvaluator;
	private Member topicMaster;
	private Member speakerOne;
	private Member evaluatorOne;
	private Member speakerTwo;
	private Member evaluatorTwo;
	private Member grammarian;
	private Member ahCounter;
	private Member timer;
	
	private SimpleDateFormat sdf = new SimpleDateFormat ("M/dd/yyyy");
	
	public Schedule (String meetingTime) {		
		try {
			this.meetingDate = sdf.parse(meetingTime);
		} catch (ParseException e) {
			System.out.println ("Cannot convert meeting time to date.");
			e.printStackTrace();
		}
	}
	
	// Used by the rules engine to see if a member is assigned
	public boolean memberAlreadyAssigned (Member member) {	
		if (toastmaster == member) return true;
		if (generalEvaluator == member) return true;
		if (topicMaster == member) return true;
		if (speakerOne == member) return true;
		if (evaluatorOne == member) return true;
		if (speakerTwo == member) return true;
		if (evaluatorTwo == member) return true;
		if (grammarian == member) return true;
		if (ahCounter == member) return true;
		if (timer == member) return true;
		
		return false;
	}

	public void printSchedule() {
		System.out.println ("\nSchedule for " + this.getMeetingTime());
		
		if (toastmaster != null) 
			System.out.println("Toastmaster: " + toastmaster.getName());
		else
			System.out.println("Toastmaster: ** unassigned **");
		
		if (generalEvaluator != null)
			System.out.println("General Evaluator: " + generalEvaluator.getName());
		else
			System.out.println("General Evaluator: ** unassigned **");
		
		if (topicMaster != null)
			System.out.println("TopicsMaster: " + topicMaster.getName());
		else
			System.out.println("TopicMaster: ** unassigned **");
		
		if (speakerOne != null)
			System.out.println("Speaker 1: " + speakerOne.getName());
		else
			System.out.println("Speaker 1: ** unassigned **");
		
		if (evaluatorOne != null)
			System.out.println("Evaluator 1: " + evaluatorOne.getName());
		else
			System.out.println("Evaluator 1: ** unassigned **");
		
		if (speakerTwo != null)
			System.out.println("Speaker 2: " + speakerTwo.getName());
		else
			System.out.println("Speaker 2: ** unassigned **");
		
		if (evaluatorTwo != null)
			System.out.println("Evaluator 2: " + evaluatorTwo.getName());
		else
			System.out.println("Evaluator 2: ** unassigned **");
		
		if (grammarian != null)
			System.out.println("Grammarian: " + grammarian.getName());
		else
			System.out.println("Grammarian: ** unassigned **");
		
		if (ahCounter != null)
			System.out.println("Ah Counter: " + ahCounter.getName());
		else
			System.out.println("Ah Counter: ** unassigned **");
		
		if(timer != null)
			System.out.println("Timer: " + timer.getName());
		else
			System.out.println("Timer: ** unassigned **");
	}
	
	public void saveSchedule() {
		MongoDatabase db = MongoInstance.getDB();
		
		Document schedule = new Document ();
		
		try {
			schedule.append("meeting date", sdf.parse(sdf.format(this.meetingDate)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// Add the working positions to the document
		if (this.toastmaster != null) schedule.append("toastmaster", this.getToastmaster().getName());
		if (this.generalEvaluator != null) schedule.append("general evaluator", this.getGeneralEvaluator().getName());
		if (this.topicMaster != null) schedule.append("topic master", this.getTopicMaster().getName());
		
		// Add the speakers to the document
		Document speakerOne; 
		if (this.speakerOne != null) {
			speakerOne = new Document().append("name", this.getSpeakerOne().getName());
		} else {
			speakerOne = new Document();
		}
		
		Document speakerTwo;
		if (this.speakerTwo != null) {
			speakerTwo = new Document().append("name", this.getSpeakerTwo().getName());
		} else {
			speakerTwo = new Document();
		}
		
		schedule.append("speaker", asList(speakerOne, speakerTwo));
		
		// Add the evaluators to the document
		Document evaluatorOne;
		if (this.evaluatorOne != null) {
			evaluatorOne = new Document().append("name", this.getEvaluatorOne().getName());
		} else {
			evaluatorOne = new Document();
		}
		
		Document evaluatorTwo;
		if (this.evaluatorTwo != null) {
			evaluatorTwo = new Document().append("name", this.getEvaluatorTwo().getName());
		} else {
			evaluatorTwo = new Document();
		}
		
		schedule.append("evaluator", asList(evaluatorOne, evaluatorTwo));
		
		// Add the rest of the working positions
		if (this.grammarian != null) schedule.append("grammarian", this.getGrammarian().getName());
		if (this.ahCounter != null) schedule.append("ah counter", this.getAhCounter().getName());
		if (this.timer != null) schedule.append("timer", this.getTimer().getName());
		
		db.getCollection("schedule").insertOne(schedule);
	}
	
	public String getMeetingTime() {
		return sdf.format(meetingDate);
	}
	public Member getToastmaster() {
		return toastmaster;
	}
	public void setToastmaster(Member toastmaster) {
		this.toastmaster = toastmaster;
	}
	public Member getGeneralEvaluator() {
		return generalEvaluator;
	}
	public void setGeneralEvaluator(Member generalEvaluator) {
		this.generalEvaluator = generalEvaluator;
	}
	public Member getTopicMaster() {
		return topicMaster;
	}
	public void setTopicMaster(Member topicMaster) {
		this.topicMaster = topicMaster;
	}
	public Member getSpeakerOne() {
		return speakerOne;
	}
	public void setSpeakerOne(Member speakerOne) {
		this.speakerOne = speakerOne;
	}
	public Member getEvaluatorOne() {
		return evaluatorOne;
	}
	public void setEvaluatorOne(Member evaluatorOne) {
		this.evaluatorOne = evaluatorOne;
	}
	public Member getSpeakerTwo() {
		return speakerTwo;
	}
	public void setSpeakerTwo(Member speakerTwo) {
		this.speakerTwo = speakerTwo;
	}
	public Member getEvaluatorTwo() {
		return evaluatorTwo;
	}
	public void setEvaluatorTwo(Member evaluatorTwo) {
		this.evaluatorTwo = evaluatorTwo;
	}
	public Member getGrammarian() {
		return grammarian;
	}
	public void setGrammarian(Member grammarian) {
		this.grammarian = grammarian;
	}
	public Member getAhCounter() {
		return ahCounter;
	}
	public void setAhCounter(Member ahCounter) {
		this.ahCounter = ahCounter;
	}
	public Member getTimer() {
		return timer;
	}
	public void setTimer(Member timer) {
		this.timer = timer;
	}
}
