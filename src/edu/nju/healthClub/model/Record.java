package edu.nju.healthClub.model;

import edu.nju.healthClub.model.user.Person;

public class Record {
	private int ID;
	private Person person;
	private ActivitySession activitySession;

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public ActivitySession getActivitySession() {
		return activitySession;
	}
	public void setActivitySession(ActivitySession activitySession) {
		this.activitySession = activitySession;
	}
}
