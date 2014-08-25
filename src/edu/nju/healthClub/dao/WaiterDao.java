package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;

public interface WaiterDao extends CommonDao{
	public ArrayList<Appointment> findAppointmentsOfActivitySession(ActivitySession activitySession);
	public ArrayList<Record> findRecordsOfActivitySession(ActivitySession activitySession);
	
	public void save(Activity activity);
	public void save(ActivitySession session);
	public void update(Activity activity);
	public void update(ActivitySession activitySession);
	public void deleteActivitySession(int activitySessionID);

	public ArrayList<Person> findPersons(String vIPID, String username,String registerDate, String name, String telephone, String email);
	public void saveRecord(int personID,int activitySessionID);
	public void deleteRecord(int personID,int activitySessionID);
}
