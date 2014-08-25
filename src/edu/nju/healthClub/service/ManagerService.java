package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.ManagerDaoImpl;
import edu.nju.healthClub.helper.AccountHelper;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;

public class ManagerService implements ManagerServiceInterface{
	
	private ManagerDaoImpl dao = new ManagerDaoImpl();
	
	@Override
	public ArrayList<String> getPersonAges() {
		ArrayList<String> ages = new ArrayList<String>();
		for(Person person : dao.getAllPerson()){
			ages.add(Integer.toString(AccountHelper.getPersonAge(person)));
		}
		return ages;
	}
	
	@Override
	public ArrayList<String> getPersonSexs() {
		ArrayList<String> sexs = new ArrayList<String>();
		for(Person person : dao.getAllPerson()){
			sexs.add(person.getSex());
		}
		return sexs;
	}
	
	@Override
	public ArrayList<String> getPersonAddresses() {
		ArrayList<String> addresses = new ArrayList<String>();
		for(Person person : dao.getAllPerson()){
			addresses.add(person.getAddress());
		}
		return addresses;
	}
	
	@Override
	public ArrayList<String> getVIPStates() {
		ArrayList<String> states = new ArrayList<String>();
		for(VIP vip : dao.getAllVIP()){
			states.add(vip.getState().toString());
		}
		return states;
	}
	
	@Override
	public ArrayList<String> getRecordsByDay() {
		ArrayList<Record> records = dao.getAllRecord();
		ArrayList<String> results = new ArrayList<String>();
		for(Record record : records){
			results.add(record.getActivitySession().getDate().toString());
		}
		return results;
	}
	
	@Override
	public ArrayList<String> getRecordsByMonth() {
		return null;
	}
	
	@Override
	public ArrayList<String> getPlacesUsage() {
		ArrayList<ActivitySession> activitySessions = dao.getAllActivitySession();
		ArrayList<String> results = new ArrayList<String>();
		for(ActivitySession activitySession : activitySessions){
			results.add(activitySession.getPlace().getName());
		}
		return results;
	}
	
	@Override
	public ArrayList<String> getCoachsUsage() {
		ArrayList<ActivitySession> activitySessions = dao.getAllActivitySession();
		ArrayList<String> results = new ArrayList<String>();
		for(ActivitySession activitySession : activitySessions){
			results.add(activitySession.getCoach().getName());
		}
		return results;
	}
}
