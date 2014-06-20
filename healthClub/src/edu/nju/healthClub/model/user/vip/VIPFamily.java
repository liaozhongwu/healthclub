package edu.nju.healthClub.model.user.vip;

import java.util.ArrayList;

import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.user.VIP;

public class VIPFamily extends VIP{
	
	private ArrayList<Person> persons;
	
	public VIPFamily(){
		setPersons(new ArrayList<Person>());
	}

	public ArrayList<Person> getPersons() {
		return persons;
	}

	public void setPersons(ArrayList<Person> persons) {
		this.persons = persons;
	}
}
