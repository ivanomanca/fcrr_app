package jaca.laggercalendar.util;

import java.util.Date;

public class LaggerEvent {
	
	private int id;
	private String description;
	private Date startDate;
	private Date endDate;
	private String address;
	private LaggerContact responsible;
	
	public LaggerEvent(int id, String description, Date startDate, Date endDate, String address, LaggerContact responsible){
		this.id = id;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.responsible = responsible;
	}
	
	public int getID(){
		return id;
	}
	
	public String getDescription(){
		return description;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
	
	public String getAddress(){
		return address;
	}
	
	public LaggerContact getResponsible(){
		return responsible;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDescription(String name) {
		this.description = name;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setResponsible(LaggerContact responsible) {
		this.responsible = responsible;
	}
}