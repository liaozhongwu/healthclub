package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.ManagerDaoImpl;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public class ManagerService implements ManagerServiceInterface{
	
	private ManagerDaoImpl dao = new ManagerDaoImpl();
	
	@Override
	public ArrayList<String> getPersonAges() {
		ArrayList<String> ages = new ArrayList<String>();
		for(VIP vip : dao.getAllVIP()){
			if(vip instanceof VIPPerson){
				ages.add(Integer.toString(((VIPPerson) vip).getPerson().getAge()));
			}else if(vip instanceof VIPFamily){
				for(Person person : ((VIPFamily) vip).getPersons()){
					ages.add(Integer.toString(person.getAge()));
				}
			}
		}
		return ages;
	}
	
	@Override
	public ArrayList<String> getPersonSexs() {
		ArrayList<String> sexs = new ArrayList<String>();
		for(VIP vip : dao.getAllVIP()){
			if(vip instanceof VIPPerson){
				sexs.add(((VIPPerson) vip).getPerson().getSex());
			}else if(vip instanceof VIPFamily){
				for(Person person : ((VIPFamily) vip).getPersons()){
					sexs.add(person.getSex());
				}
			}
		}
		return sexs;
	}
	
	@Override
	public ArrayList<String> getPersonAddresses() {
		ArrayList<String> addresses = new ArrayList<String>();
		for(VIP vip : dao.getAllVIP()){
			if(vip instanceof VIPPerson){
				addresses.add(((VIPPerson) vip).getPerson().getAddress());
			}else if(vip instanceof VIPFamily){
				for(Person person : ((VIPFamily) vip).getPersons()){
					addresses.add(person.getAddress());
				}
			}
		}
		return addresses;
	}
	
	@Override
	public ArrayList<String> getVIPStates() {
		ArrayList<String> states = new ArrayList<String>();
		for(VIP vip : dao.getAllVIP()){
			states.add(vip.getState());
		}
		return states;
	}
	
	@Override
	public ArrayList<String> getRecordsByDay() {
		ArrayList<Record> records = dao.getAllRecord();
		ArrayList<ActivitySession> sessions = dao.getAllActivitySession();
		ArrayList<String> results = new ArrayList<String>();
		for(Record record : records){
			for(ActivitySession session : sessions){
				if(record.getActivityID() == session.getActivityID() && record.getSession() == session.getSession()){
					results.add(session.getDate().toString());
				}
			}
		}
		return results;
	}
	
	@Override
	public ArrayList<String> getRecordsByMonth() {
		return null;
	}
	
	@Override
	public ArrayList<String> getPlacesUsage() {
		ArrayList<ActivitySession> sessions = dao.getAllActivitySession();
		ArrayList<String> results = new ArrayList<String>();
		for(ActivitySession session : sessions){
			results.add(session.getPlace());
		}
		return results;
	}
	
	@Override
	public ArrayList<String> getCoachsUsage() {
		ArrayList<ActivitySession> sessions = dao.getAllActivitySession();
		ArrayList<String> results = new ArrayList<String>();
		for(ActivitySession session : sessions){
			for(Coach coach : session.getCoachs()){
				results.add(coach.getName());
			}
		}
		return results;
	}
}
