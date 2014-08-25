package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.WaiterDaoImpl;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;

public class WaiterService extends CommonService implements WaiterServiceInterface{
	private WaiterDaoImpl dao = new WaiterDaoImpl();

	@Override
	public void create(Activity activity) {
		dao.save(activity);
	}
	@Override
	public void modify(Activity activity) {
		dao.update(activity);
	}
	@Override
	public void add(ActivitySession activitySession){
		dao.save(activitySession);
	}
	@Override
	public void modify(ActivitySession activitySession){
		dao.update(activitySession);
	}
	@Override
	public void removeActivitySession(int activitySessionID){
		dao.deleteActivitySession(activitySessionID);
	}
	@Override
	public ArrayList<Appointment> getAppointmentsOfActivitySession(ActivitySession activitySession) {
		return dao.findAppointmentsOfActivitySession(activitySession);
	}
	@Override
	public ArrayList<Record> getRecordsOfActivitySession(ActivitySession activitySession) {
		return dao.findRecordsOfActivitySession(activitySession);
	}

	@Override
	public void createRecord(int personID, int activitySessionID) {
		dao.saveRecord(personID, activitySessionID);
	}
	@Override
	public Record findRecord(int personID, int activitySessionID) {
		return dao.queryRecord(personID, activitySessionID);
	}
	@Override
	public void removeRecord(int personID, int activitySessionID) {
		dao.deleteRecord(personID, activitySessionID);
	}
	@Override
	public ArrayList<Person> searchPersons(String VIPID,String username,String registerDate,String name,String telephone,String email) {
		return dao.findPersons(VIPID, username, registerDate, name, telephone, email);
	}
}
