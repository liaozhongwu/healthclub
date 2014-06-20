package edu.nju.healthClub.model.user.vip;

import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.user.VIP;

public class VIPPerson extends VIP{
	private Person person;
	
	public VIPPerson(){
		setPerson(new Person());
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
