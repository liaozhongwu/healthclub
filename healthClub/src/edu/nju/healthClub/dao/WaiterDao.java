package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Record;

public interface WaiterDao {

	public void save(Activity activity);
	public void save(ActivitySession session);
	public void save(Record record);
	public void saveRecord(String VIPID,String personID,String activityID,String activitySession);
	public Coach findCoach(String col,String value);
	public ArrayList<Coach> findCoachs(String key);
	public ArrayList<Appointment> findAppointmentsByActivity(String activityID,String activitySession);
	public Record findRecord(String VIPID,String personID,String activityID,String activitySession);
	public ArrayList<Record> findRecordsByActivity(String ActivityID,String SessionID);
	public void update(Activity activity);
	public void remove(ActivitySession activitySession);
	public void remove(Record record);
	public void removeRecord(String VIPID,String personID,String activityID,String activitySession);
}
