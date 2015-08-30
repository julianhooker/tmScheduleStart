package com.schedule.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.mongodb.client.MongoDatabase;

import com.schedule.database.MongoInstance;

public class TMScheduleTest {
	
	public static final void main(String[] args) {		
		KieServices kieServices = KieServices.Factory.get();
    	KieContainer kContainer = kieServices.getKieClasspathContainer();
    	
    	// Create a collection of new schedules
    	ArrayList<Schedule> schedules = new ArrayList<Schedule> ();
    	
    	// Loop through a date range
    	for (LocalDate ld = LocalDate.of(2015, 9, 2); ld.isBefore(LocalDate.of(2015, 10, 1)); ld = ld.plusWeeks(1)) {
    		// Create the new rules space
    		KieSession ksession = kContainer.newKieSession("ksession-rules");
    		
    		// Create a schedule for this date and add it to the rules space
    		Schedule schedule = new Schedule (ld.format(DateTimeFormatter.ofPattern("M/dd/yyyy")));
    		ksession.insert(schedule);
    		
    		// Get a list of members
        	ArrayList<Member> members = getMembers(MongoInstance.getDB());
    		
    		// Randomize the members and add them to the KIE session
        	Collections.shuffle(members);
      
        	for (Member m: members) {
        		ksession.insert(m);
        	}
    		
        	// Fire the rules
        	ksession.fireAllRules();
        	
        	// Add the newly created schedule to the collections of schedules. 
        	schedules.add(schedule);
    	}
    	
    	schedules.forEach(schedule -> schedule.saveSchedule());
    	
    	schedules.forEach(schedule -> schedule.printSchedule());
    }
	
	private static ArrayList<Member> getMembers(MongoDatabase db) {
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		Member mem;  
		
//		mem = new Member ("Robyne", "Vaughn");
//		memberList.add(mem);
		
		mem = new Member ("Andy", "Gerron");
		memberList.add(mem);

		mem = new Member ("Julian", "Hooker");
		memberList.add(mem);
		
		mem = new Member ("Jason", "Davis");
		memberList.add(mem);
		
		mem = new Member ("Gary", "Mims");
		memberList.add(mem);
		
		mem = new Member ("Gary", "Johnson");
		memberList.add(mem);
		
//		mem = new Member ("Tedd", "Fargason");
//		memberList.add(mem);
		
		mem = new Member ("Spenser", "Piercy");
		memberList.add(mem);
		
		mem = new Member ("Edd", "Dallishaw");
		memberList.add(mem);
		
		mem = new Member ("Darrell", "Bateman");
		memberList.add(mem);

		mem = new Member ("Glenn", "Lowrance");
		memberList.add(mem);
		
		return memberList;
	}
}
