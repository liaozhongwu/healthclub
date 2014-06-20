package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.dao.UserDaoImpl;
import edu.nju.healthClub.helper.SearchHelper;
import edu.nju.healthClub.model.*;
import edu.nju.healthClub.model.user.*;
import edu.nju.healthClub.model.user.vip.*;

public class UserService implements UserServiceInterface{
	
	private UserDaoImpl dao = new UserDaoImpl();
	
	@Override
	public User validateUser(String username, String password) {
		// TODO Auto-generated method stub
		SearchHelper helper = new SearchHelper();
		String type = helper.getUserSearchType(username);
		User user = dao.findUser(type, username);
		if(user == null){
			return InvalidUser.invalidUser(type+"Unexited");
		}else{
			if(user.getPassword().equals(password)){
				return user;
			}else{
				return InvalidUser.invalidUser("passwordError");
			}
		}
	}	
	@Override
	public User getUserByID(String ID) {
		// TODO Auto-generated method stub
		SearchHelper helper = new SearchHelper();
		String type = helper.getUserSearchType(ID);
		if(type.equals("ID")){
			User user = dao.findUser(type, ID);
			return user;
		}else{
			return null;
		}
	}
	@Override
	public User searchUser(String col,String key) {
		// TODO Auto-generated method stub
		User user = dao.findUser(col, key);
		return user;
	}
	
	@Override
	public void update(User user) {

		// TODO Auto-generated method stub
		if(user instanceof VIPPerson){
			dao.update((VIPPerson) user);
		}else if(user instanceof VIPFamily){
			VIPFamily exitedUser = (VIPFamily)getUserByID(Integer.toString(user.getID()));
			ArrayList<Person> persons = ((VIPFamily) user).getPersons();
			ArrayList<Person> exitedPersons = exitedUser.getPersons();
			for(Person exitedPerson : exitedPersons){
				boolean exited = false;
				for(Person person : persons){
					if(exitedPerson.getID() == person.getID())
						exited = true;
				}
				if(!exited)
					dao.remove(exitedPerson);
			}
			for(Person person : persons){
				if(dao.findPerson("ID", Integer.toString(person.getID())) == null){
					dao.save(person,exitedUser);
				}	
			}
			dao.update((VIPFamily) user);
		}
	}
	
	@Override
	public Person getPersonByID(String ID) {
		// TODO Auto-generated method stub
		return dao.findPerson("ID", ID);
	}
	
	@Override
	public Activity getActivityByID(String ID) {
		// TODO Auto-generated method stub
		return dao.findActivity("ID", ID);
	}
	
	@Override
	public ArrayList<Activity> getActivityByKey(String key) {
		// TODO Auto-generated method stub
		return dao.findActivities(key);
	}
	
	@Override
	public ActivitySession getActivitySessionByID(String ID, String session) {
		// TODO Auto-generated method stub
		Activity activity = dao.findActivity("ID", ID);
		ArrayList<ActivitySession> activitySessions = activity.getSessions();
		for(ActivitySession activitySession : activitySessions){
			if(Integer.toString(activitySession.getSession()).equals(session)){
				return activitySession;
			}
		}
		return null;
	}
	
	@Override
	public ActivitySession getActivitySessionByID(int ID,int session) {
		// TODO Auto-generated method stub
		return getActivitySessionByID(Integer.toString(ID), Integer.toString(session));
	}
	
	@Override
	public void create(Appointment appointment) {
		// TODO Auto-generated method stub
		dao.save(appointment);
	}
	
	@Override
	public void remove(Appointment appointment) {
		// TODO Auto-generated method stub
		dao.remove(appointment);
	}
	
	@Override
	public void removeAppointment(String VIPID, String personID,
			String activityID, String activitySession) {
		// TODO Auto-generated method stub
		dao.removeAppointment(VIPID, personID, activityID, activitySession);
	}
	
	@Override
	public void createAppointment(String VIPID, String personID,
			String activityID, String activitySession) {
		// TODO Auto-generated method stub
		dao.saveAppointment(VIPID, personID, activityID, activitySession);
	}
	
	@Override
	public Appointment findAppointment(String VIPID, String personID,
			String activityID, String activitySession) {
		// TODO Auto-generated method stub
		return dao.findAppointment(VIPID, personID, activityID, activitySession);
	}
	
	@Override
	public ArrayList<Appointment> getAppointmentsByVIP(String VIPID) {
		// TODO Auto-generated method stub
		return dao.findAppointmentsByVIP(VIPID); 
	}
	@Override
	public ArrayList<Appointment> getAppointmentsByVIP(int VIPID) {
		// TODO Auto-generated method stub
		return dao.findAppointmentsByVIP(Integer.toString(VIPID));
	}
	@Override
	public ArrayList<Record> getRecordsByVIP(String VIPID) {
		// TODO Auto-generated method stub
		return dao.findRecordsByVIP(VIPID);
	}
	@Override
	public ArrayList<Record> getRecordsByVIP(int VIPID) {
		// TODO Auto-generated method stub
		return dao.findRecordsByVIP(Integer.toString(VIPID));
	}
	@Override
	public Activity getActivityByID(int ID) {
		// TODO Auto-generated method stub
		return getActivityByID(Integer.toString(ID));
	}
	@Override
	public ArrayList<Payment> getPaymentByVIP(String VIPID) {
		// TODO Auto-generated method stub
		return dao.findPaymentsByVIP(VIPID);
	}
	@Override
	public ArrayList<Payment> getPaymentByVIP(int VIPID) {
		// TODO Auto-generated method stub
		return dao.findPaymentsByVIP(Integer.toString(VIPID));
	}
	
	@Override
	public String getUserType(String userID) {
		// TODO Auto-generated method stub
		int ID = Integer.parseInt(userID);
		if(ID < 10){
			return "Admin";
		}else if(ID < 100){
			return "Manager";
		}else if(ID < 1000){
			return "Waiter";
		}else{
			return "VIP";
		}
	}
}
