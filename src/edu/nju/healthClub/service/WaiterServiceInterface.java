package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;

public interface WaiterServiceInterface extends CommonServiceInterface{
	public void create(Activity activity);
	public void modify(Activity activity);
	public void add(ActivitySession activitySession);
	public void modify(ActivitySession activitySession);
	public void removeActivitySession(int activitySessionID);
	public ArrayList<Appointment> getAppointmentsOfActivitySession(ActivitySession activitySession);
	public ArrayList<Record> getRecordsOfActivitySession(ActivitySession activitySession);
	public void createRecord(int personID,int activitySessionID);
	public Record findRecord(int personID,int activitySessionID);
	public void removeRecord(int personID,int activitySessionID);
	public ArrayList<Person> searchPersons(String VIPID,String username,String registerDate,String name,String telephone,String email);
}
