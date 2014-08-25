package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;
import edu.nju.healthClub.model.user.Waiter;

public class UserDaoImpl implements UserDao{
	protected DBConnImpl db;

	public UserDaoImpl(){
		db = new DBConnImpl();
	}
	@Override
	public User findUser(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = conn.prepareStatement("select * from user where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				User temp;
				if((temp = queryAdmin(ID)) != null)
					user = temp;
				else if((temp = queryManager(ID)) != null)
					user = temp;
				else if((temp = queryWaiter(ID)) != null)
					user = temp;
				else if((temp = queryVIP(ID)) != null)
					user = temp;
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return user;
	}
	@Override
	public User findUser(String username) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		User user = null;
		try {
			statement = conn.prepareStatement("select * from user where username = ?");
			statement.setString(1, username);
			rs = statement.executeQuery();
			if(rs.next()){
				int ID = rs.getInt("ID");
				User temp;
				if((temp = queryAdmin(ID)) != null)
					user = temp;
				else if((temp = queryManager(ID)) != null)
					user = temp;
				else if((temp = queryWaiter(ID)) != null)
					user = temp;
				else if((temp = queryVIP(ID)) != null)
					user = temp;
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return user;
	}
	@Override
	public Admin queryAdmin(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Admin admin = null;
		try {
			statement = conn.prepareStatement("select * from user,admin where user.ID = ? and admin.ID = user.ID");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				admin = new Admin();
				admin.setID(ID);
				admin.setUsername(rs.getString("username"));
				admin.setPassword(rs.getString("password"));
				admin.setName(rs.getString("name"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return admin;
	}

	@Override
	public Manager queryManager(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Manager manager = null;
		try {
			statement = conn.prepareStatement("select * from user,manager where user.ID = ? and manager.ID = user.ID");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				manager = new Manager();
				manager.setID(ID);
				manager.setUsername(rs.getString("username"));
				manager.setPassword(rs.getString("password"));
				manager.setName(rs.getString("name"));
				return manager;
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return manager;
	}

	@Override
	public Waiter queryWaiter(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Waiter waiter = null;
		try {
			statement = conn.prepareStatement("select * from user,waiter where user.ID = ? and waiter.ID = user.ID");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				waiter = new Waiter();
				waiter.setID(ID);
				waiter.setUsername(rs.getString("username"));
				waiter.setPassword(rs.getString("password"));
				waiter.setName(rs.getString("name"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return waiter;
	}

	@Override
	public VIP queryVIP(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		VIP vip = null;
		try {
			statement = conn.prepareStatement("select * from user,vip where user.ID = ? and vip.ID = user.ID");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				vip = new VIP();
				vip.setID(ID);
				vip.setUsername(rs.getString("username"));
				vip.setPassword(rs.getString("password"));
				vip.setType(VIPType.valueOf(rs.getString("type")));
				vip.setBalance(rs.getInt("balance"));
				vip.setRegisterDate(rs.getString("registerDate"));
				vip.setState(VIPState.valueOf(rs.getString("state")));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return vip;
	}
	@Override
	public Person queryPerson(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Person person = null;
		try {
			statement = conn.prepareStatement("select * from person where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			while(rs.next()){
				person = new Person();
				person.setID(rs.getInt("ID"));
				person.setVIP(queryVIP(rs.getInt("VIPID")));
				person.setName(rs.getString("name"));
				person.setSex(rs.getString("sex"));
				person.setBirthday(rs.getString("birthday"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setResident(rs.getString("resident"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return person;
	}
	@Override
	public ArrayList<Person> queryPersonsOfVIP(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		try {
			statement = conn.prepareStatement("select * from person where VIPID = ?");
			statement.setInt(1, vip.getID());
			rs = statement.executeQuery();
			while(rs.next()){
				Person person = new Person();
				person.setVIP(vip);
				person.setID(rs.getInt("ID"));
				person.setName(rs.getString("name"));
				person.setSex(rs.getString("sex"));
				person.setBirthday(rs.getString("birthday"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setResident(rs.getString("resident"));
				persons.add(person);
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return persons;
	}
	@Override
	public Payment queryPayment(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Payment payment = null;
		try {
			statement = conn.prepareStatement("select * from payment where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				payment = new Payment();
				payment.setID(ID);
				payment.setVIP(queryVIP(rs.getInt("VIPID")));
				payment.setDate(rs.getString("date"));
				payment.setBankCard(rs.getInt("bankcard"));
				payment.setPayment(rs.getInt("payment"));
				payment.setRemark(rs.getString("remark"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return payment;
	}

	@Override
	public Activity queryActivity(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Activity activity = null;
		try {
			statement = conn.prepareStatement("select * from activity where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				activity = new Activity();
				activity.setID(ID);
				activity.setName(rs.getString("name"));
				activity.setIntroduction(rs.getString("introduction"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return activity;
	}

	@Override
	public ActivitySession queryActivitySession(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ActivitySession activitySession = null;
		try {
			statement = conn.prepareStatement("select * from activitySession where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			while(rs.next()){
				activitySession = new ActivitySession();
				activitySession.setID(rs.getInt("ID"));
				activitySession.setActivity(queryActivity(rs.getInt("activityID")));
				activitySession.setName(rs.getString("name"));
				activitySession.setDate(rs.getString("date"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(queryPlace(rs.getInt("placeID")));
				activitySession.setCoach(queryCoach(rs.getInt("coachID")));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return activitySession;
	}
	@Override
	public ArrayList<ActivitySession> queryActivitySessionsOfActivity(Activity activity) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<ActivitySession> activitySessions = new ArrayList<ActivitySession>();
		try {
			statement = conn.prepareStatement("select * from activitySession where activityID = ?");
			statement.setInt(1, activity.getID());
			rs = statement.executeQuery();
			while(rs.next()){
				ActivitySession activitySession = new ActivitySession();
				activitySession.setActivity(activity);
				activitySession.setID(rs.getInt("ID"));
				activitySession.setName(rs.getString("name"));
				activitySession.setDate(rs.getString("date"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(queryPlace(rs.getInt("placeID")));
				activitySession.setCoach(queryCoach(rs.getInt("coachID")));
				activitySessions.add(activitySession);
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return activitySessions;
	}
	@Override
	public Place queryPlace(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Place place = null;
		try {
			statement = conn.prepareStatement("select * from place where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				place = new Place(ID);
				place.setName(rs.getString("name"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return place;
	}

	@Override
	public Coach queryCoach(int ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Coach coach = null;
		try {
			statement = conn.prepareStatement("select * from coach where ID = ?");
			statement.setInt(1, ID);
			rs = statement.executeQuery();
			if(rs.next()){
				coach = new Coach(ID);
				coach.setName(rs.getString("name"));
				coach.setSex(rs.getString("sex"));
				coach.setMajor(rs.getString("major"));
				coach.setIntroduction(rs.getString("introduction"));
				coach.setPortrait(rs.getString("portrait"));
			}
			db.closePreparedStatement(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
		return coach;
	}
	@Override
	public Record queryRecord(int personID, int activitySessionID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Record record = null;
		try {
			statement = conn.prepareStatement("select * from record where personID = ? and activitySessionID = ?");
			statement.setInt(1, personID);
			statement.setInt(2, activitySessionID);
			rs = statement.executeQuery();
			while(rs.next()){
				record = new Record();
				record.setPerson(queryPerson(rs.getInt("personID")));
				record.setActivitySession(queryActivitySession(rs.getInt("activitysessionID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return record;
	}
	@Override
	public Appointment queryAppointment(int personID, int activitySessionID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		Appointment appointment = null;
		try {
			statement = conn.prepareStatement("select * from appointment where personID = ? and activitySessionID = ?");
			statement.setInt(1, personID);
			statement.setInt(2, activitySessionID);
			rs = statement.executeQuery();
			while(rs.next()){
				appointment = new Appointment();
				appointment.setPerson(queryPerson(rs.getInt("personID")));
				appointment.setActivitySession(queryActivitySession(rs.getInt("activitysessionID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return appointment;
	}

}
