package edu.nju.healthClub.model.user;

import edu.nju.healthClub.model.User;


public class Manager extends User{
	private String name;
	
	public Manager(){
		name = "";
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
