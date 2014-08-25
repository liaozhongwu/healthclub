package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.CommonDaoImpl;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;

public class CommonService extends UserService implements CommonServiceInterface{
	
	private CommonDaoImpl dao = new CommonDaoImpl();

	@Override
	public VIP getVIPByID(int VIPID) {
		return dao.queryVIP(VIPID);
	}
	@Override
	public Person getPersonByID(int personID) {
		return dao.queryPerson(personID);
	}

	@Override
	public void modify(VIP vip) {
		dao.update(vip);
	}
	@Override
	public ArrayList<Person> getPersonsOfVIP(VIP vip) {
		return dao.queryPersonsOfVIP(vip);
	}
	
	@Override
	public ActivitySession getActivitySessionByID(int ID) {
		return dao.queryActivitySession(ID);
	}
	
	@Override
	public void createAppointment(int personID, int activitySessionID) {
		dao.saveAppointment(personID, activitySessionID);
	}
	@Override
	public Appointment findAppointment(int personID, int activitySessionID) {
		return dao.queryAppointment(personID, activitySessionID);
	}
	@Override
	public void removeAppointment(int personID, int activitySessionID) {
		dao.deleteAppointment(personID, activitySessionID);
	}
	
	@Override
	public ArrayList<Appointment> getAppointmentsOfVIP(int VIPID) {
		return dao.findAppointmentsOfVIP(VIPID); 
	}
	@Override
	public ArrayList<Record> getRecordsOfVIP(int VIPID) {
		return dao.findRecordsOfVIP(VIPID);
	}
	@Override
	public ArrayList<Payment> getPaymentsOfVIP(int VIPID) {
		return dao.findPaymentsOfVIP(VIPID);
	}
	@Override
	public void add(Person person) {
		dao.save(person);
	}
	@Override
	public void modify(Person person) {
		dao.update(person);
	}
	@Override
	public void removePerson(int personID) {
		dao.deletePerson(personID);
	}
	@Override
	public Activity getActivityByID(int ID) {
		return dao.queryActivity(ID);
	}
	@Override
	public ArrayList<ActivitySession> getActivitySessionsOfActivity(Activity activity) {
		return dao.queryActivitySessionsOfActivity(activity);
	}

	@Override
	public ArrayList<Coach> searchCoach(String key){
		return dao.findCoachs(key);
	}
	@Override
	public ArrayList<Place> searchPlace(String key){
		return dao.findPlaces(key);
	}
	@Override
	public ArrayList<ActivitySession> searchActivitySessions(String activityID,String name,String date,String time,String placeID,String coachID) {
		return dao.findActivitySessions(activityID, name, date, time, placeID, coachID);
	}
	@Override
	public ArrayList<ActivitySession> getTodayActivitySessions() {
		return dao.findTodayActivitySessions();
	}
	@Override
	public ArrayList<ActivitySession> getTomorrowActivitySessions() {
		return dao.findTomorrowActivitySessions();
	}
}
