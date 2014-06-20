package edu.nju.healthClub.service;

import java.util.ArrayList;

import edu.nju.healthClub.model.*;

public interface UserServiceInterface {
	public User validateUser(String username,String password);
	public User getUserByID(String username);
	public User searchUser(String col,String key);
	public void update(User user);
	public Person getPersonByID(String ID);
	public Activity getActivityByID(String ID);
	public Activity getActivityByID(int ID);
	public ArrayList<Activity> getActivityByKey(String key);
	public ActivitySession getActivitySessionByID(String ID,String session);
	public ActivitySession getActivitySessionByID(int ID, int session);
	public void create(Appointment appointment);
	public void createAppointment(String VIPID,String personID,String activityID,String activitySession);
	public ArrayList<Appointment> getAppointmentsByVIP(String VIPID);
	public ArrayList<Appointment> getAppointmentsByVIP(int VIPID);
	public Appointment findAppointment(String VIPID,String personID,String activityID,String activitySession);
	public void remove(Appointment appointment);
	public void removeAppointment(String VIPID,String personID,String activityID,String activitySession);
	public ArrayList<Record> getRecordsByVIP(String VIPID);
	public ArrayList<Record> getRecordsByVIP(int VIPID);
	public ArrayList<Payment> getPaymentByVIP(String VIPID);
	public ArrayList<Payment> getPaymentByVIP(int VIPID);
	public String getUserType(String userID);
}
