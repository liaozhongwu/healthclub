package edu.nju.healthClub.model;

import java.util.ArrayList;

public class Activity {
	private int ID;
	private String name;
	private String introduction;
	private ArrayList<ActivitySession> sessions;
	
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public ArrayList<ActivitySession> getSessions() {
		return sessions;
	}
	public void setSessions(ArrayList<ActivitySession> sessions) {
		this.sessions = sessions;
	}
}
