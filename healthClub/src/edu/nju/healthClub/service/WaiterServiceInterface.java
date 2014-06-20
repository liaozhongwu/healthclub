package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Record;

public interface WaiterServiceInterface {
	
	public Coach getCoachByID(String ID);
	public ArrayList<Coach> getCoachByKey(String key);
	public void create(Activity activity);
	public void update(Activity activity);
	public ArrayList<Appointment> getAppointmentsByActivity(String activityID,String session);
	public ArrayList<Appointment> getAppointmentsByActivity(int activityID,int session);
	public void create(Record record);
	public void createRecord(String VIPID,String personID,String activityID,String activitySession);
	public ArrayList<Record> getRecordsByActivity(String activityID,String session);
	public ArrayList<Record> getRecordsByActivity(int activityID,int session);
	public Record findRecord(String VIPID,String personID,String activityID,String activitySession);
	public void remove(Record record);
	public void removeRecord(String VIPID,String personID,String activityID,String activitySession);
}
