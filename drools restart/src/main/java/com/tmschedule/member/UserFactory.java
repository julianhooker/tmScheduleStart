package com.tmschedule.member;

// Factory to get users
public class UserFactory {
	
	// Get member classes
	public static Member getMember (String firstName, String lastName) {
		return new Member (firstName, lastName);
	}
}
