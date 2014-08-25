package edu.nju.healthClub.model;

public class Place {
	private int ID;
	private String name;
	
	public Place(int ID){
		this.ID = ID;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
