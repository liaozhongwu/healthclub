package edu.nju.healthClub.model;

public class Record {
	private int VIPID;
	private Person person;
	private int activityID;
	private int session;
	public int getVIPID() {
		return VIPID;
	}
	public void setVIPID(int vIPID) {
		VIPID = vIPID;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	
}
