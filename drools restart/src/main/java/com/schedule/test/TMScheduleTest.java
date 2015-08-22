package com.schedule.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class TMScheduleTest {
	
	public static final void main(String[] args) {
        try {
        	
        	KieServices kieServices = KieServices.Factory.get();
        	KieContainer kContainer = kieServices.getKieClasspathContainer();
        	
        	// Connect to the database
    		MongoClient mongoClient = new MongoClient("localhost", 27017);
    		MongoDatabase db = mongoClient.getDatabase("julian");
        	
        	// Create a collection of new schedules
        	ArrayList<Schedule> schedules = new ArrayList<Schedule> ();
        	
        	// Get a list of members
        	ArrayList<Member> members = getMembers(db);
        	
        	// Loop through a date range
        	for (LocalDate ld = LocalDate.of(2015, 8, 12); ld.isBefore(LocalDate.of(2015, 10, 31)); ld = ld.plusWeeks(1)) {
        		// Create the new rules space
        		KieSession ksession = kContainer.newKieSession("ksession-rules");
        		
        		// Create a schedule for this date and add it to the rules space
        		Schedule schedule = new Schedule (ld.format(DateTimeFormatter.ofPattern("M/dd/yyyy")));
        		ksession.insert(schedule);
        		
        		// Shuffle the members to make them random
            	Collections.shuffle(members);
            	
            	// Add the members to the rule KIE space
            	for (Member m: members) {
            		ksession.insert(m);
            	}
        		
            	// Fire the rules
            	ksession.fireAllRules();
            	
            	// Add the newly created schedule to the collections of schedules. 
            	schedules.add(schedule);
        	}
        	
        	schedules.forEach(schedule -> schedule.printSchedule());
        	
        	mongoClient.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	private static ArrayList<Member> getMembers(MongoDatabase db) {
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		Member mem;  
		
//		mem = new Member ("Robyne", "Vaughn", db);
//		memberList.add(mem);
		
		mem = new Member ("Andy", "Gerron", db);
		memberList.add(mem);

		mem = new Member ("Julian", "Hooker", db);
		memberList.add(mem);
		
		mem = new Member ("Jason", "Davis", db);
		memberList.add(mem);
		
		mem = new Member ("Gary", "Mims", db);
		memberList.add(mem);
		
		mem = new Member ("Gary", "Johnson", db);
		memberList.add(mem);
		
//		mem = new Member ("Tedd", "Fargason", db);
//		memberList.add(mem);
		
		mem = new Member ("Spenser", "Piercy", db);
		memberList.add(mem);
		
		mem = new Member ("Edd", "Dallishaw", db);
		memberList.add(mem);
		
		mem = new Member ("Darrell", "Bateman", db);
		memberList.add(mem);

		mem = new Member ("Glenn", "Lowrance", db);
		memberList.add(mem);
		
		return memberList;
	}
}
