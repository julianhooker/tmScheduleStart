package com.schedule.test;

import java.util.ArrayList;

public class Member {
	private String firstName;
	private String lastName;

	private int numberOfSpeeches = 0;
	private int numberOfEvaluations = 0;
	private int numberTimesAsGeneralEvaluator = 0;
	private int numberOfMeetingsAttended = 0;
	
	private ArrayList<String> previousRole = new ArrayList<String> ();
	
	public boolean recentlyServedInRole (String role) {
		boolean served = false;
		
		if (previousRole.contains(role)) served = true;
		
		return served;
	}
	
	public void addPreviousRole (String role) {
		
		previousRole.add(role);
		
		if (previousRole.size() > 3) {
			previousRole.remove(0);
		} 
	}
	
	public int getNumberOfSpeeches() {
		return numberOfSpeeches;
	}

	public void setNumberOfSpeeches(int numberOfSpeeches) {
		this.numberOfSpeeches = numberOfSpeeches;
	}
	
	public Member (String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getName () {
		return this.getFirstName() + " " + this.getLastName();
	}

	public int getNumberOfEvaluations() {
		return numberOfEvaluations;
	}

	public void setNumberOfEvaluations(int numberOfEvaluations) {
		this.numberOfEvaluations = numberOfEvaluations;
	}

	public int getNumberTimesAsGeneralEvaluator() {
		return numberTimesAsGeneralEvaluator;
	}

	public void setNumberTimesAsGeneralEvaluator(
			int numberTimesAsGeneralEvaluator) {
		this.numberTimesAsGeneralEvaluator = numberTimesAsGeneralEvaluator;
	}

	public int getNumberOfMeetingsAttended() {
		return numberOfMeetingsAttended;
	}

	public void setNumberOfMeetingsAttended(int numberOfMeetingsAttended) {
		this.numberOfMeetingsAttended = numberOfMeetingsAttended;
	}
}
