package com.schedule.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class TMScheduleTest {
	
	public static final void main(String[] args) {
        try {
        	
        	KieServices kieServices = KieServices.Factory.get();
        	KieContainer kContainer = kieServices.getKieClasspathContainer();
        	
        	// Create a collection of new schedules
        	ArrayList<Schedule> schedules = new ArrayList<Schedule> ();
        	
        	// Get a list of members
        	ArrayList<Member> members = getMembers();
        	
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
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	private static ArrayList<Member> getMembers() {
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
		
		mem = new Member ("Edd", "Dallashaw");
		memberList.add(mem);
		
		mem = new Member ("Darrell", "Bateman");
		memberList.add(mem);

		mem = new Member ("Glenn", "Lowrance");
		memberList.add(mem);
		
		return memberList;
	}
}
