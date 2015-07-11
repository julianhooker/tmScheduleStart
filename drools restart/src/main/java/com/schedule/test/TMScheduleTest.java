package com.schedule.test;

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
        	KieSession ksession = kContainer.newKieSession("ksession-rules");
        	
        	ArrayList<Member> members = getMembers();
        	Collections.shuffle(members);
        	
        	for (Member m: members) {
        		ksession.insert(m);
        	}
        	
        	for (int i = 1; i < 3; i++) {
        		Schedule schedule = new Schedule (i + "/11/2015");
            	
        		ksession.insert(schedule);
//            	ksession.fireAllRules();
//            	schedule.printSchedule();
        	}
        	
        	ksession.fireAllRules();
        	
        	ksession.dispose();
        	
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	private static ArrayList<Member> getMembers() {
		ArrayList<Member> memberList = new ArrayList<Member>();
		
		Member mem;  
		
//		mem = new Member ("Robyne", "Vaughn");
//		mem.setNumberOfSpeeches(3);
//		mem.setNumberOfEvaluations(3);
//		mem.setNumberOfMeetingsAttended(6);
//		mem.setNumberTimesAsGeneralEvaluator(0);
//		mem.addPreviousRole("Grammarian");
//		memberList.add(mem);

		
		mem = new Member ("Andy", "Gerron");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);

		mem = new Member ("Julian", "Hooker");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
		mem = new Member ("Jason", "Davis");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		mem.addPreviousRole("Speaker");
		memberList.add(mem);
		
		mem = new Member ("Gary", "Mims");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
		mem = new Member ("Gary", "Johnson");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
//		mem = new Member ("Tedd", "Fargason");
//		mem.setNumberOfSpeeches(1);
//		mem.setNumberOfEvaluations(1);
//		mem.setNumberOfMeetingsAttended(1);
//		mem.setNumberTimesAsGeneralEvaluator(0);
//		mem.addPreviousRole("Grammarian");
//		memberList.add(mem);
		
		mem = new Member ("Spenser", "Piercy");
		mem.setNumberOfSpeeches(2);
		mem.setNumberOfEvaluations(0);
		mem.setNumberOfMeetingsAttended(4);
		mem.setNumberTimesAsGeneralEvaluator(0);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
		mem = new Member ("Edd", "Dallashaw");
		mem.setNumberOfSpeeches(4);
		mem.setNumberOfEvaluations(4);
		mem.setNumberOfMeetingsAttended(4);
		mem.setNumberTimesAsGeneralEvaluator(4);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
		mem = new Member ("Darrell", "Bateman");
		mem.setNumberOfSpeeches(6);
		mem.setNumberOfEvaluations(6);
		mem.setNumberOfMeetingsAttended(6);
		mem.setNumberTimesAsGeneralEvaluator(6);
		mem.addPreviousRole("Grammarian");
		memberList.add(mem);
		
		return memberList;
	}

}
