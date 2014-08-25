package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;

public interface CommonDao extends UserDao{
	public void update(VIP vip);
	public void save(Person person);
	public void update(Person person);
	public void deletePerson(int personID);
	public void saveAppointment(int personID,int activitySessionID);
	public void deleteAppointment(int personID,int activitySessionID);
	public ArrayList<Appointment> findAppointmentsOfVIP(int VIPID);
	public ArrayList<Record> findRecordsOfVIP(int VIPID);
	public ArrayList<Payment> findPaymentsOfVIP(int VIPID);
	
	public ArrayList<Coach> findCoachs(String key);
	public ArrayList<Place> findPlaces(String key);
	public ArrayList<ActivitySession> findTodayActivitySessions();
	public ArrayList<ActivitySession> findTomorrowActivitySessions();
	public ArrayList<ActivitySession> findActivitySessions(String activityID, String name, String date, String time, String placeID, String CoachID);
}
