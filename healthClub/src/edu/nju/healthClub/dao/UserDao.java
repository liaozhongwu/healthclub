package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.User;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public interface UserDao {
	public void save(VIPPerson VIPperson);
	public void save(VIPFamily VIPfamily);
	public void save(Coach coach);
	public void save(Appointment appointment);
	public void saveAppointment(String VIPID,String personID,String activityID,String activitySession);
	public void save(Person person,VIP vip);
	public User findUser(String col,String value);
	public Person findPerson(String col,String value);
	public Activity findActivity(String col,String value);
	public ArrayList<Activity> findActivities(String key);
	public Appointment findAppointment(String VIPID,String personID,String activityID,String activitySession);
	public ArrayList<Appointment> findAppointmentsByVIP(String VIPID);
	public ArrayList<Record> findRecordsByVIP(String VIPID);
	public ArrayList<Payment> findPaymentsByVIP(String VIPID);
	public void update(VIPPerson VIPperson);
	public void update(VIPFamily VIPfamily);
	public void update(Person person);
	public void remove(Person person);
	public void remove(Appointment appointment);
	public void removeAppointment(String VIPID,String personID,String activityID,String activitySession);	
}
