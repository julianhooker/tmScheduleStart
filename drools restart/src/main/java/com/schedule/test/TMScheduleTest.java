package com.schedule.test;

import java.util.ArrayList;
import java.util.Collections;

import org.bson.Document;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

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
        	
//        	getPastSchedules();
        	
        	for (int i = 1; i < 10; i++) {
        		Schedule schedule = new Schedule (i + "/11/2015");
            	
        		ksession.insert(schedule);
            	ksession.fireAllRules();
            	schedule.printSchedule();
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
	
	private static ArrayList<Schedule> getPastSchedules() {
		final ArrayList<Schedule> schedules = new ArrayList<Schedule>();
		
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase("julian");

		FindIterable<Document> iterable = db.getCollection("tmschedule").find();
		
		iterable.forEach(new Block<Document>() {
			public void apply(final Document document) {
				Schedule schedule = new Schedule(document.getDate("meeting date"));
				
				if (document.getString("toastmaster") != null)
					schedule.setToastmaster(new Member (document.getString("toastmaster")));
				if (document.getString("general evaluator") != null)
					schedule.setGeneralEvaluator(new Member (document.getString("general evaluator")));
				if (document.getString("topic master") != null)
					schedule.setTopicMaster(new Member (document.getString("topic master")));
				if (document.getString("speaker one") != null)
					schedule.setSpeakerOne(new Member (document.getString("speaker one")));
				if (document.getString("evaluator one") != null)
					schedule.setEvaluatorOne(new Member (document.getString("evaluator one")));
				if (document.getString("speaker two") != null)
					schedule.setSpeakerTwo(new Member (document.getString("speaker two")));
				if (document.getString("evaluator two") != null)
					schedule.setEvaluatorTwo(new Member (document.getString("evaluator two")));
				if (document.getString("grammarian") != null)
					schedule.setGrammarian(new Member (document.getString("grammarian")));
				if (document.getString("ah counter") != null)
					schedule.setAhCounter(new Member (document.getString("ah counter")));
				if (document.getString("timer") != null)
					schedule.setTimer(new Member (document.getString("timer")));
				
				schedules.add(schedule);
	        
		        schedule.printSchedule();
		    }
		});
		
		return schedules; 
	}

}
