package jaca.laggercalendar.util;

public class LaggerContact {
	
	private String name;
	private String surname;
	private String number;
	
	public LaggerContact(String contact){
		String[] data = contact.split("#");
		name = data[0];
		surname = data[1];
		number = data[2];
	}
	
	public LaggerContact(String name, String surname, String number){
		this.name = name;
		this.surname = surname;
		this.number = number;	
	}
	
	public String getName(){
		return name;
	}
	
	public String getSurname(){
		return surname;
	}
	
	public String getNumber(){
		return number;
	}
}