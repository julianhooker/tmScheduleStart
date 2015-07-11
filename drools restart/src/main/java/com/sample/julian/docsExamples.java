package com.sample.julian;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class docsExamples {
	
	public static final void main(String[] args) {
        try {
        	
        	KieServices kieServices = KieServices.Factory.get();
        	KieContainer kContainer = kieServices.getKieClasspathContainer();
        	KieSession ksession = kContainer.newKieSession("ksession-rules");
        	
        	String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        	Map<String,Room> name2room = new HashMap<String,Room>();

        	for (String name: names) {
        		Room room = new Room( name );
        		name2room.put( name, room );
        		ksession.insert(room);
        		Sprinkler sprinkler = new Sprinkler (room);
        		ksession.insert(sprinkler);        		
        	}
        	
        	ksession.fireAllRules();
        	
        	System.out.println("Setting some fires in the Kitchen and the Office.");
        	
        	Fire kitchenFire = new Fire (name2room.get("kitchen"));
        	Fire officeFire = new Fire (name2room.get("office"));
        	
        	FactHandle kitchenFireHandle = ksession.insert(kitchenFire);
        	FactHandle officeFireHandle = ksession.insert(officeFire);
        	
        	ksession.fireAllRules();
        	
        	System.out.println("Putting out the fire in the Kitchen.");

        	ksession.delete (kitchenFireHandle);
        	
        	ksession.fireAllRules();
        	
        	System.out.println("Putting out the fire in the Office");
        	
        	ksession.delete(officeFireHandle);
        	
        	ksession.fireAllRules();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
	
	public static class Room {
		private String name;

		public Room(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}	
	}
	
	public static class Sprinkler {
		private Room room; 
		private boolean on;
		
		public Sprinkler(Room room) {
			this.room = room;
		}
		
		public Room getRoom() {
			return room;
		}
		public void setRoom(Room room) {
			this.room = room;
		}
		public boolean isOn() {
			return on;
		}
		public void setOn(boolean on) {
			this.on = on;
		}
	}
	
	public static class Fire {
		private Room room;
		
		private Fire(Room room) {
			this.room = room;
		}

		public Room getRoom() {
			return room;
		}

		public void setRoom(Room room) {
			this.room = room;
		}
	}
	
	public static class Alarm {
	}

}
