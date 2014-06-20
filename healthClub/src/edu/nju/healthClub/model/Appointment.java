package edu.nju.healthClub.model;

import java.util.Date;

public class Appointment {
	private int vipID;
	private Person person;
	private int activityID;
	private int session;
	private Date date;
	public int getVIPID() {
		return vipID;
	}
	public void setVIPID(int vipID) {
		this.vipID = vipID;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getActivityID() {
		return activityID;
	}
	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}
	
}
