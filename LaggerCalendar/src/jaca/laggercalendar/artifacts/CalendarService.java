package jaca.laggercalendar.artifacts;

import jaca.laggercalendar.util.LaggerContact;
import jaca.laggercalendar.util.LaggerEvent;

import java.util.Date;
import java.util.HashMap;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class CalendarService extends Artifact {
	
	private int id = 0;
	private HashMap<Integer,LaggerEvent> calendar;
	
	public void init() {
		// load event database
		calendar = new HashMap<Integer, LaggerEvent>();
		calendar.put(++id, new LaggerEvent(id,"Business meeting @ Unibo",new Date(2011,12,20,11,00),new Date(2011,12,12,12,00),"via Pisacane 11, Cesena",new LaggerContact("Alessandro","Montalti","3333505300")));
		calendar.put(++id, new LaggerEvent(id,"Dinner @ÊHome",new Date(2011,12,20,20,00),new Date(2011,12,12,18,00),"via Mulini 22, Cesena",new LaggerContact("Andrea","Zagnoli","34054123123")));
	}
	
	@OPERATION void addEvent(String name,Date startDate, Date endDate, String address, String responsible){
		calendar.put(++id, new LaggerEvent(id,name,startDate,endDate,address, new LaggerContact(responsible)));
	}
	
	@OPERATION void deleteEvent(int id){
		calendar.remove(id);
	}
	
	@OPERATION void getEvent(int id, OpFeedbackParam<LaggerEvent> ev){
		ev.set(calendar.get(id));
	}
	
	@OPERATION void modifyEvent(int id,String name,Date startDate,Date endDate,String address,LaggerContact responsible){
		calendar.remove(id);
		calendar.put(id,new LaggerEvent(id,name,startDate,endDate,address,responsible));
	}
}