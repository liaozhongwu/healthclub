package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.WaiterDaoImpl;
import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Record;

public class WaiterService extends UserService implements WaiterServiceInterface{
	private WaiterDaoImpl dao = new WaiterDaoImpl();
	
	@Override
	public Coach getCoachByID(String ID) {
		// TODO Auto-generated method stub
		return dao.findCoach("ID", ID);
	}
	
	@Override
	public ArrayList<Coach> getCoachByKey(String key){
		// TODO Auto-generated method stub
		return dao.findCoachs(key);
	}
	
	@Override
	public void create(Activity activity) {
		// TODO Auto-generated method stub
		dao.save(activity);
	}
	

	@Override
	public void update(Activity activity) {
		// TODO Auto-generated method stub
		dao.update(activity);
	}
	
	@Override
	public ArrayList<Appointment> getAppointmentsByActivity(String activityID,String session){
		return dao.findAppointmentsByActivity(activityID,session);
	}
	
	@Override
	public ArrayList<Appointment> getAppointmentsByActivity(int activityID, int session) {
		// TODO Auto-generated method stub
		return getAppointmentsByActivity(Integer.toString(activityID), Integer.toString(session));
	}
	

	@Override
	public void create(Record record) {
		// TODO Auto-generated method stub
		dao.save(record);
	}
	
	@Override
	public ArrayList<Record> getRecordsByActivity(String activityID,String session) {
		// TODO Auto-generated method stub
		return dao.findRecordsByActivity(activityID,session);
	}
	
	@Override
	public ArrayList<Record> getRecordsByActivity(int activityID, int session) {
		// TODO Auto-generated method stub
		return getRecordsByActivity(Integer.toString(activityID), Integer.toString(session));
	}
	
	@Override
	public Record findRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		// TODO Auto-generated method stub
		return dao.findRecord(VIPID, personID, activityID, activitySession);
	}
	
	@Override
	public void remove(Record record) {
		// TODO Auto-generated method stub
		dao.remove(record);
	}
	
	@Override
	public void createRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		// TODO Auto-generated method stub
		dao.saveRecord(VIPID, personID, activityID, activitySession);
	}
	
	@Override
	public void removeRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		// TODO Auto-generated method stub
		dao.removeRecord(VIPID, personID, activityID, activitySession);
	}
}
