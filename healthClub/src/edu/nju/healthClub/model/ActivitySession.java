package edu.nju.healthClub.model;

import java.util.ArrayList;
import java.util.Date;

public class ActivitySession {
	private int activityID;
	private int session;
	private Date date;
	private String starttime;
	private String endtime;
	private String place;
	private ArrayList<Coach> coachs;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public ArrayList<Coach> getCoachs() {
		return coachs;
	}
	public void setCoachs(ArrayList<Coach> coachs) {
		this.coachs = coachs;
	}
}
