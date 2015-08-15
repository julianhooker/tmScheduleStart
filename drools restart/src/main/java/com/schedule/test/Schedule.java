package com.schedule.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Schedule {
	private Date meetingTime;
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
	
	public Schedule (Date meetingTime) {
		this.meetingTime = meetingTime; 
	}
	
	public Schedule (String meetingTime) {
		try {
			this.meetingTime = sdf.parse(meetingTime);
		} catch (ParseException e) {
			System.out.println ("Cannot convert meeting time to date.");
			e.printStackTrace();
		}
	}
	
//	public boolean memberNotAssigned(Member member) {
//		boolean assigned = false;
//		
//		if (toastmaster != null && member.getName().equals(toastmaster.getName())) return true;
//		if (generalEvaluator != null && member.getName().equals(generalEvaluator.getName())) return true;
//		
//		return assigned; 
//	}
	
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
//	
//	public String getAssignment (Member member) {
//		String memberName = member.getName();
//		
//		if (memberName.equals(toastmaster.getName())) return "Toastmaster";
//		if (memberName.equals(generalEvaluator.getName())) return "General Evaluator";
//		if (memberName.equals(topicMaster.getName())) return "Topic Master"; 
//		if (memberName.equals(speakerOne.getName())) return "Speaker";
//		if (memberName.equals(evaluatorOne.getName())) return "Evaluator";
//		if (memberName.equals(speakerTwo.getName())) return "Speaker";
//		if (memberName.equals(evaluatorTwo.getName())) return "Evaluator";
//		if (memberName.equals(grammarian.getName())) return "Grammarian";
//		if (memberName.equals(ahCounter.getName())) return "Ah Counter";
//		if (memberName.equals(timer.getName())) return "Timer";
//		
//		return null; 
//	}
	
//	public void clearSchedule() {
//		this.ahCounter = null;
//		this.evaluatorOne = null; 
//		this.evaluatorTwo = null;
//		this.generalEvaluator = null;
//		this.grammarian = null;
//		this.speakerOne = null;
//		this.speakerTwo = null;
//		this.timer = null;
//		this.toastmaster = null;
//		this.topicMaster = null;
//	}
	
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
	
	public String getMeetingTime() {
		return sdf.format(meetingTime);
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
