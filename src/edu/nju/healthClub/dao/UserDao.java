package edu.nju.healthClub.dao;

import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Admin;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.User;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.Waiter;

public interface UserDao {
	public User findUser(int ID);
	public User findUser(String username);
	public Admin queryAdmin(int ID);
	public Manager queryManager(int ID);
	public Waiter queryWaiter(int ID);
	public VIP queryVIP(int ID);
	public Person queryPerson(int ID);
	public ArrayList<Person> queryPersonsOfVIP(VIP vip);
	public Payment queryPayment(int ID);
	public Activity queryActivity(int ID);
	public ActivitySession queryActivitySession(int ID);
	public ArrayList<ActivitySession> queryActivitySessionsOfActivity(Activity activity);
	public Place queryPlace(int ID);
	public Coach queryCoach(int ID);
	public Record queryRecord(int personID,int activitySessionID);
	public Appointment queryAppointment(int personID,int activitySessionID);
}
