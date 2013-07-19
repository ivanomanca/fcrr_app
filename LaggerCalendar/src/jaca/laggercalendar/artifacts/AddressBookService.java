package jaca.laggercalendar.artifacts;

import jaca.laggercalendar.util.LaggerContact;

import java.util.HashMap;

import cartago.Artifact;
import cartago.OPERATION;
import cartago.OpFeedbackParam;

public class AddressBookService extends Artifact {
	
	private int id = -1;
	private HashMap<Integer,LaggerContact> addressbook;
	
	public void init() {
		
		addressbook = new HashMap<Integer, LaggerContact>();
		addressbook.put(++id, new LaggerContact("Alessandro","Montalti","3333505300"));
		addressbook.put(++id, new LaggerContact("Marco","Santarelli","3490707214"));
		addressbook.put(++id, new LaggerContact("Matteo","Desanti","3404500123"));
		addressbook.put(++id, new LaggerContact("Andrea","Zagnoli","3405567102"));
		
	}
	
	@OPERATION void addContact(String name, String surname, String number){
		addressbook.put(++id, new LaggerContact(name,surname,number));
	}

	@OPERATION void deleteContact(int id){
		addressbook.remove(id);
	}
	
	@OPERATION void searchContact(int id, OpFeedbackParam<LaggerContact> contact){
		contact.set(addressbook.get(id));
	}
	
	@OPERATION void modifyContact(int id,String name,String surname,String number){
		addressbook.remove(id);
		addressbook.put(id,new LaggerContact(name,surname,number));
	}
}