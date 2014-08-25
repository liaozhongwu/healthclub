package edu.nju.healthClub.model;

import edu.nju.healthClub.model.user.Person;

public class Appointment {
	private Person person;
	private ActivitySession activitySession;
	private String date;

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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
