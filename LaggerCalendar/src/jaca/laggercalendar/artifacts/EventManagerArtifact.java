package jaca.laggercalendar.artifacts;

import jaca.android.dev.GUIArtifact;
import jaca.android.dev.IJaCaActivity;
import jaca.laggercalendar.EventListActivity;
import jaca.laggercalendar.R;
import jaca.laggercalendar.util.LaggerContact;
import jaca.laggercalendar.util.LaggerEvent;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import android.os.Bundle;
import android.view.MenuItem;
import cartago.INTERNAL_OPERATION;
import cartago.OPERATION;


public class EventManagerArtifact extends GUIArtifact {

	private EventListActivity viewer;
	
	private HashMap<Integer,LaggerEvent> calendar;
	private int id = 0; 
	private final static long ADVANCE_TIME = (1000 * 60 * 60);
	private final static long POSTPONEMENT_TIME = (1000 * 60 * 15);
	private boolean first = true; 
	protected void init(IJaCaActivity activity, Bundle savedInstanceState) {
		super.init(activity, savedInstanceState);
		viewer = (EventListActivity) activity;
		calendar = new HashMap<Integer, LaggerEvent>();
		calendar.put(++id, new LaggerEvent(id,"Meeting @ ApiCe",new GregorianCalendar(2011,12,20,14,15).getTime(),new GregorianCalendar(2011,12,20,15,15).getTime(),"via Genova 181, Cesena",new LaggerContact("Andrea","Santi","3337654321")));
		calendar.put(++id, new LaggerEvent(id,"Tutorial @ Faculty of Engeneering",new GregorianCalendar(2011,12,20,16,30).getTime(),new GregorianCalendar(2011,12,20,18,30).getTime(),"viale Risorgimento 2, Bologna",new LaggerContact("Alessandro","Ricci","3401234567")));
		calendar.put(++id, new LaggerEvent(id,"Lesson LMC-LS @ Ing2",new GregorianCalendar(2011,12,21,8,10).getTime(),new GregorianCalendar(2011,12,21,12,30).getTime(),"via Genova 181, Cesena",new LaggerContact("Alessandro","Montalti","3333505300")));
		calendar.put(++id, new LaggerEvent(id,"Lesson SISMA-LS @ Ing2",new GregorianCalendar(2011,12,21,14,30).getTime(),new GregorianCalendar(2011,12,21,18,30).getTime(),"via Genova 181, Cesena",new LaggerContact("Alessandro","Montalti","3333505300")));
		calendar.put(++id, new LaggerEvent(id,"Working @ ApiCe",new GregorianCalendar(2011,12,22,14,20).getTime(),new GregorianCalendar(2011,12,22,17,30).getTime(),"via Genova 181, Cesena",new LaggerContact("Alessandro","Montalti","3333505300")));
		calendar.put(++id, new LaggerEvent(id,"Exam @ Faculty of Engeneering",new GregorianCalendar(2011,12,24,12,45).getTime(),new GregorianCalendar(2011,12,24,15,45).getTime(),"vial Risorgimento 2, Bologna",new LaggerContact("Andrea","Zagnoli","34054123123")));
		this.loadCalendar();
		this.linkOnOptionsItemSelectedToOp("onOptionsItemSelected");
	}
	 
    @OPERATION void addEvent(String descr, Date startDate, Date endDate, String address, String responsible){
    	calendar.put(++id, new LaggerEvent(id, descr, startDate,endDate, address, new LaggerContact(responsible)));
    	viewer.append(startDate, endDate, descr);
    	Date now = new Date();
		long delay = (startDate.getTime() - now.getTime()) - ADVANCE_TIME;
		execInternalOp("scheduleEventNotification", id, delay);
    }
    
    @OPERATION void modifyEvent(Integer id, String descr, Date startDate, Date endDate, String address, String responsible){
    	LaggerEvent ev = calendar.get(id);
    	ev.setAddress(address);
    	ev.setStartDate(endDate);
    	ev.setEndDate(endDate);
    	ev.setResponsible(new LaggerContact(responsible));
    	calendar.put(id, new LaggerEvent(id, descr, startDate,endDate, address, new LaggerContact(responsible)));
    	//Not completely exact. A new notification is added but the previous one is not removed.
    	Date now = new Date();
		long delay = (startDate.getTime() - now.getTime()) - ADVANCE_TIME;
		execInternalOp("scheduleEventNotification", id, delay); 
    }

    @OPERATION void loadCalendar(){

		Iterator<Entry<Integer,LaggerEvent>> i = calendar.entrySet().iterator();
		while(i.hasNext()){
			Map.Entry<Integer,LaggerEvent> me = (Map.Entry<Integer,LaggerEvent>) i.next();
			LaggerEvent e = (LaggerEvent) me.getValue();
			viewer.append(e.getStartDate(),e.getEndDate(),e.getDescription());
			Date now = new Date();
			long delay = (e.getStartDate().getTime() - now.getTime()) - ADVANCE_TIME;
			if (first) {
				execInternalOp("scheduleEventNotification", e.getID(), 5000);
				first = false;
			} else {
				execInternalOp("scheduleEventNotification", e.getID(), delay);
			}
		}		
    }
   
    @OPERATION void showEventReminder(LaggerEvent event){
    	viewer.showEventReminder(event.getId(), event.getDescription(), event.getStartDate());
    }

    @OPERATION void postponeEventNotification(LaggerEvent event){
    	Date now = new Date();
    	Date startDate = event.getStartDate();
    	if ((now.getTime() + POSTPONEMENT_TIME) - startDate.getTime() <= 0){
    		execInternalOp("scheduleEventNotification", id, POSTPONEMENT_TIME);
    	}
    }
    
    @INTERNAL_OPERATION void scheduleEventNotification(int id, long delay){
    	await_time(delay);
		signal("check_schedule_tick", calendar.get(id));
    }
    
    @INTERNAL_OPERATION public void onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_add:
        	signal("add_new_event_menu_click");
        break;
        }
    }
    
    @OPERATION public void showDelayManagementWindow(Integer estimateArrival){
    	viewer.showDelayManagementWindow(new Date(estimateArrival));
    }
} 