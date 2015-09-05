package com.tmschedule.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import org.bson.Document;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.tmschedule.database.MongoInstance;
import com.tmschedule.member.Member;
import com.tmschedule.schedule.Schedule;

public class TMScheduleTest {
	
	public static final void main(String[] args) {		
		KieServices kieServices = KieServices.Factory.get();
    	KieContainer kContainer = kieServices.getKieClasspathContainer();
    	
    	// Create a collection of new schedules
    	ArrayList<Schedule> schedules = new ArrayList<Schedule> ();
    	
		// Get a list of members
    	ArrayList<Member> members = getMembers();
    	
    	// Loop through a date range
    	for (LocalDate ld = LocalDate.of(2015, 9, 2); ld.isBefore(LocalDate.of(2015, 12, 1)); ld = ld.plusWeeks(1)) {
    		// Create the new rules space
    		KieSession ksession = kContainer.newKieSession("ksession-rules");
    		
    		// Create a schedule for this date and add it to the rules space
    		Schedule schedule = new Schedule (ld.format(DateTimeFormatter.ofPattern("M/dd/yyyy")));
    		ksession.insert(schedule);
    		
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
    	
//    	schedules.forEach(schedule -> schedule.saveSchedule());
    	
    	schedules.forEach(schedule -> schedule.printSchedule());
    }
	
	private static ArrayList<Member> getMembers() {
		ArrayList<Member> memberList = new ArrayList<Member>();		
		
		MongoCursor<Document> cursor = MongoInstance.getDB().getCollection("members")
				.find(new Document ("active", "true")).iterator();
		try {
		    while (cursor.hasNext()) {
		        Document doc = cursor.next();
		        
		        memberList.add(new Member(
		        		doc.getString("first name"), 
		        		doc.getString("last name")));
		    }
		} finally {
		    cursor.close();
		}
		
		return memberList;
	}
}
