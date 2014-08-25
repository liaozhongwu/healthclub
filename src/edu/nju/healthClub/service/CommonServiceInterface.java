package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;

public interface CommonServiceInterface extends UserServiceInterface{
	public VIP getVIPByID(int VIPID);
	public Person getPersonByID(int personID);
	public void modify(VIP vip);
	public ArrayList<Person> getPersonsOfVIP(VIP vip);
	public void add(Person person);
	public void modify(Person person);
	public void removePerson(int personID);
	public Activity getActivityByID(int ID);
	public ActivitySession getActivitySessionByID(int ID);
	public ArrayList<ActivitySession> getActivitySessionsOfActivity(Activity activity);
	
	public void createAppointment(int personID,int activitySessionID);
	public Appointment findAppointment(int personID,int activitySessionID);
	public void removeAppointment(int personID,int activitySessionID);
	public ArrayList<Appointment> getAppointmentsOfVIP(int VIPID);
	public ArrayList<Record> getRecordsOfVIP(int VIPID);
	public ArrayList<Payment> getPaymentsOfVIP(int VIPID);
	
	public ArrayList<Coach> searchCoach(String key);
	public ArrayList<Place> searchPlace(String key);
	public ArrayList<ActivitySession> getTodayActivitySessions();
	public ArrayList<ActivitySession> getTomorrowActivitySessions();
	public ArrayList<ActivitySession> searchActivitySessions(String activityID,String name,String date,String time,String placeID,String coachID);
}
