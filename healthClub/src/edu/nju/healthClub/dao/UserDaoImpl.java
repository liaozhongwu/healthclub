package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.*;
import edu.nju.healthClub.model.user.*;
import edu.nju.healthClub.model.user.vip.*;

public class UserDaoImpl implements UserDao{
	
	private DBConnImpl db = new DBConnImpl();
	
	@Override
	public void save(VIPPerson VIPperson) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from person");
			rs = statement.executeQuery();
			if(rs.next()){
				VIPperson.getPerson().setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, VIPperson.getPerson().getID());
			statement.setString(2, VIPperson.getPerson().getPetname());
			statement.setInt(3, VIPperson.getPerson().getAge());
			statement.setString(4, VIPperson.getPerson().getSex());
			statement.setString(5, VIPperson.getPerson().getAddress());
			statement.setString(6, VIPperson.getPerson().getTelephone());
			statement.setString(7, VIPperson.getPerson().getEmail());
			statement.setString(8, VIPperson.getPerson().getRealname());
			statement.setString(9, VIPperson.getPerson().getResidentID());
			statement.setDate(10, new java.sql.Date(VIPperson.getPerson().getBirthday().getTime()));	
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("select max(ID) from vipcard where type = 'person'");
			rs = statement.executeQuery();
			if(rs.next()){
				VIPperson.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into vipcard values(?,?,?,?,?)");
			statement.setInt(1, VIPperson.getID());
			statement.setString(2, "person");
			statement.setInt(3, VIPperson.getBalance());
			statement.setDate(4, new java.sql.Date(VIPperson.getRegisterDate().getTime()));
			statement.setString(5, "active");
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into vipperson values(?,?)");
			statement.setInt(1, VIPperson.getID());
			statement.setInt(2, VIPperson.getPerson().getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, VIPperson.getID());
			statement.setString(2, VIPperson.getUsername());
			statement.setString(3, VIPperson.getPassword());
			statement.setString(4, "VIPperson");
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	@Override
	public void save(VIPFamily VIPfamily) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			ArrayList<Person> persons = VIPfamily.getPersons();
			for(Person person : persons){
				//获取personID
				statement = conn.prepareStatement("select max(ID) from person");
				rs = statement.executeQuery();
				if(rs.next()){
					person.setID(rs.getInt("max(ID)") + 1);
				}
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				//保存person
				statement = conn.prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?,?)");
				statement.setInt(1, person.getID());
				statement.setString(2, person.getPetname());
				statement.setInt(3, person.getAge());
				statement.setString(4, person.getSex());
				statement.setString(5, person.getAddress());
				statement.setString(6, person.getTelephone());
				statement.setString(7, person.getEmail());
				statement.setString(8, person.getRealname());
				statement.setString(9, person.getResidentID());
				statement.setDate(10, new java.sql.Date(person.getBirthday().getTime()));
				statement.executeUpdate();
				db.closePreparedStatement(statement);
			}
			
			//获取VIPCardID
			statement = conn.prepareStatement("select max(ID) from vipcard where type = 'family'");
			rs = statement.executeQuery();
			if(rs.next()){
				VIPfamily.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			//保存VIPCard
			statement = conn.prepareStatement("insert into vipcard values(?,?,?,?,?)");
			statement.setInt(1, VIPfamily.getID());
			statement.setString(2, "family");
			statement.setInt(3, VIPfamily.getBalance());
			statement.setDate(4, new java.sql.Date(VIPfamily.getRegisterDate().getTime()));
			statement.setString(5, "active");
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			//保存VIPCard与person关联
			for(Person person : persons){
				statement = conn.prepareStatement("insert into vipperson values(?,?)");
				statement.setInt(1, VIPfamily.getID());
				statement.setInt(2, person.getID());
				statement.executeUpdate();
				db.closePreparedStatement(statement);
			}
			//保存user
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, VIPfamily.getID());
			statement.setString(2, VIPfamily.getUsername());
			statement.setString(3, VIPfamily.getPassword());
			statement.setString(4, "VIPfamily");
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	@Override
	public void save(Coach coach){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			//获取coachID
			statement = conn.prepareStatement("select max(ID) from coach");
			rs = statement.executeQuery();
			if(rs.next()){
				coach.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			//保存coach
			statement = conn.prepareStatement("insert into coach values(?,?,?,?,?,?)");
			statement.setInt(1, coach.getID());
			statement.setString(2, coach.getName());
			statement.setString(3, coach.getSex());
			statement.setString(4, coach.getMajor());
			statement.setString(5, coach.getIntroduction());
			statement.setString(6, coach.getPortrait());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}
	
	@Override
	public void save(Appointment appointment){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into appointment values(?,?,?,?,?)");
			statement.setInt(1, appointment.getVIPID());
			statement.setInt(2, appointment.getPerson().getID());
			statement.setInt(3, appointment.getActivityID());
			statement.setInt(4, appointment.getSession());
			statement.setDate(5, new java.sql.Date(appointment.getDate().getTime()));
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}
	
	@Override
	public void saveAppointment(String VIPID, String personID,
			String activityID, String activitySession) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into appointment values(?,?,?,?,?)");
			statement.setString(1, VIPID);
			statement.setString(2, personID);
			statement.setString(3, activityID);
			statement.setString(4, activitySession);
			statement.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}
	
	@Override
	public void save(Person person,VIP vip) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement("select max(ID) from person");
			rs = statement.executeQuery();
			if(rs.next()){
				person.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, person.getID());
			statement.setString(2, person.getPetname());
			statement.setInt(3, person.getAge());
			statement.setString(4, person.getSex());
			statement.setString(5, person.getAddress());
			statement.setString(6, person.getTelephone());
			statement.setString(7, person.getEmail());
			statement.setString(8, person.getRealname());
			statement.setString(9, person.getResidentID());
			statement.setDate(10, new java.sql.Date(person.getBirthday().getTime()));	
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			//保存VIPCard与person关联
			statement = conn.prepareStatement("insert into vipperson values(?,?)");
			statement.setInt(1, vip.getID());
			statement.setInt(2, person.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}
	
	@Override
	public User findUser(String col, String value) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from user where "+col+" = ?");
			statement.setString(1, value);
			rs = statement.executeQuery();
			if(rs.next()){
				int ID = rs.getInt("ID");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String type = rs.getString("type");
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				switch(type){
				case "manager":
					statement = conn.prepareStatement("select * from manager where ID = ?");
					statement.setInt(1, ID);
					rs = statement.executeQuery();
					if(rs.next()){
						Manager manager = new Manager();
						manager.setID(ID);
						manager.setUsername(username);
						manager.setPassword(password);
						manager.setName(rs.getString("name"));
						return manager;
					}
				case "waiter":
					statement = conn.prepareStatement("select * from waiter where ID = ?");
					statement.setInt(1, ID);
					rs = statement.executeQuery();
					if(rs.next()){
						Waiter waiter = new Waiter();
						waiter.setID(ID);
						waiter.setUsername(username);
						waiter.setPassword(password);
						waiter.setName(rs.getString("name"));
						return waiter;
					}
				case "VIPperson":
					statement = conn.prepareStatement("select * from vipcard,vipperson,person where vipcard.ID = ? and vipperson.VIPID = vipcard.ID and vipperson.personID = person.ID");
					statement.setInt(1, ID);
					rs = statement.executeQuery();
					if(rs.next()){
						VIPPerson VIPperson = new VIPPerson();
						VIPperson.setID(ID);
						VIPperson.setUsername(username);
						VIPperson.setPassword(password);
						VIPperson.setBalance(rs.getInt("balance"));
						VIPperson.setRegisterDate(rs.getDate("registerDate"));
						VIPperson.setState(rs.getString("state"));
						Person person = new Person();
						person.setID(rs.getInt("person.ID"));
						person.setPetname(rs.getString("petname"));
						person.setSex(rs.getString("sex"));
						person.setAddress(rs.getString("address"));
						person.setTelephone(rs.getString("telephone"));
						person.setEmail(rs.getString("email"));
						person.setRealname(rs.getString("realname"));
						person.setResidentID(rs.getString("residentID"));
						person.setBirthday(rs.getDate("birthday"));
						VIPperson.setPerson(person);
						return VIPperson;
					}
				case "VIPfamily":
					statement = conn.prepareStatement("select * from vipcard where vipcard.ID = ?");
					statement.setInt(1, ID);
					rs = statement.executeQuery();
					VIPFamily VIPfamily = null;
					if(rs.next()){
						VIPfamily = new VIPFamily();
						VIPfamily.setID(ID);
						VIPfamily.setUsername(username);
						VIPfamily.setPassword(password);
						VIPfamily.setBalance(rs.getInt("balance"));
						VIPfamily.setRegisterDate(rs.getDate("registerDate"));
						VIPfamily.setState(rs.getString("state"));
						db.closeResult(rs);
						db.closePreparedStatement(statement);
						statement = conn.prepareStatement("select * from vipperson,person where vipperson.VIPID = ? and vipperson.personID = person.ID");
						statement.setInt(1, ID);
						rs = statement.executeQuery();
						ArrayList<Person> persons = new ArrayList<Person>();
						while(rs.next()){
							Person person = new Person();
							person.setID(rs.getInt("person.ID"));
							person.setPetname(rs.getString("petname"));
							person.setSex(rs.getString("sex"));
							person.setAddress(rs.getString("address"));
							person.setTelephone(rs.getString("telephone"));
							person.setEmail(rs.getString("email"));
							person.setRealname(rs.getString("realname"));
							person.setResidentID(rs.getString("residentID"));
							person.setBirthday(rs.getDate("birthday"));
							persons.add(person);
						}
						VIPfamily.setPersons(persons);
					}
					return VIPfamily;
				case "admin":
					statement = conn.prepareStatement("select * from admin where ID = ?");
					statement.setInt(1, ID);
					rs = statement.executeQuery();
					if(rs.next()){
						Admin admin = new Admin();
						admin.setID(ID);
						admin.setUsername(username);
						admin.setPassword(password);
						return admin;
					}
				default:
					return null;
				}
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}

	@Override
	public Person findPerson(String col, String value) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from person where "+col+" = ?");
			statement.setString(1, value);
			rs = statement.executeQuery();
			if(rs.next()){
				Person person = new Person();
				person.setID(rs.getInt("ID"));
				person.setPetname(rs.getString("petname"));
				person.setSex(rs.getString("sex"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setRealname(rs.getString("realname"));
				person.setResidentID(rs.getString("residentID"));
				person.setBirthday(rs.getDate("birthday"));
				return person;
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}

	@Override
	public Activity findActivity(String col,String value){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from activity where "+col+" = ?");
			statement.setString(1, value);
			rs = statement.executeQuery();
			if(rs.next()){
				Activity activity = new Activity();
				activity.setID(rs.getInt("ID"));
				activity.setName(rs.getString("name"));
				activity.setIntroduction(rs.getString("introduction"));
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				statement = conn.prepareStatement("select * from activitysession where activityID = ?");
				statement.setInt(1, activity.getID());
				rs = statement.executeQuery();
				ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
				while(rs.next()){
					ActivitySession session = new ActivitySession();
					session.setActivityID(activity.getID());
					session.setSession(rs.getInt("session"));
					session.setDate(rs.getDate("date"));
					session.setStarttime(rs.getString("starttime"));
					session.setEndtime(rs.getString("endtime"));
					session.setPlace(rs.getString("place"));
					sessions.add(session);
				}
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				for(ActivitySession session : sessions){
					statement = conn.prepareStatement("select * from activitycoach,coach where activityID = ? and session = ? and activitycoach.coachID = coach.ID");
					statement.setInt(1, activity.getID());
					statement.setInt(2, session.getSession());
					rs = statement.executeQuery();
					ArrayList<Coach> coachs = new ArrayList<Coach>();
					while(rs.next()){
						Coach coach = new Coach();
						coach.setID(rs.getInt("coachID"));
						coach.setName(rs.getString("name"));
						coach.setSex(rs.getString("sex"));
						coach.setMajor(rs.getString("major"));
						coach.setIntroduction(rs.getString("introduction"));
						coach.setPortrait(rs.getString("portrait"));
						coachs.add(coach);
					}
					session.setCoachs(coachs);
				}
				activity.setSessions(sessions);
				return activity;
			}
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}

	@Override
	public ArrayList<Activity> findActivities(String key) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from activity where ID like '%" + key + "%' or name like '%" + key + "%' or introduction like '%" + key + "%'");
			rs = statement.executeQuery();
			ArrayList<Activity> activities = new ArrayList<Activity>();
			while(rs.next()){
				Activity activity = new Activity();
				activity.setID(rs.getInt("ID"));
				activity.setName(rs.getString("name"));
				activity.setIntroduction(rs.getString("introduction"));
				activities.add(activity);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			for(Activity activity : activities){
				statement = conn.prepareStatement("select * from activitysession where activityID = ?");
				statement.setInt(1, activity.getID());
				rs = statement.executeQuery();
				ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
				while(rs.next()){
					ActivitySession session = new ActivitySession();
					session.setActivityID(activity.getID());
					session.setSession(rs.getInt("session"));
					session.setDate(rs.getDate("date"));
					session.setStarttime(rs.getString("starttime"));
					session.setEndtime(rs.getString("endtime"));
					session.setPlace(rs.getString("place"));
					sessions.add(session);
				}
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				for(ActivitySession session : sessions){
					statement = conn.prepareStatement("select * from activitycoach,coach where activityID = ? and session = ? and activitycoach.coachID = coach.ID");
					statement.setInt(1, activity.getID());
					statement.setInt(2, session.getSession());
					rs = statement.executeQuery();
					ArrayList<Coach> coachs = new ArrayList<Coach>();
					while(rs.next()){
						Coach coach = new Coach();
						coach.setID(rs.getInt("coachID"));
						coach.setName(rs.getString("name"));
						coach.setSex(rs.getString("sex"));
						coach.setMajor(rs.getString("major"));
						coach.setIntroduction(rs.getString("introduction"));
						coach.setPortrait(rs.getString("portrait"));
						coachs.add(coach);
					}
					session.setCoachs(coachs);
				}
				activity.setSessions(sessions);
			}
			return activities;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}

	@Override
	public Appointment findAppointment(String VIPID, String personID,
			String activityID, String activitySession) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from appointment,person where VIPID = ? and personID = ? and activityID = ? and session = ? and appointment.personID = person.ID");
			statement.setString(1, VIPID);
			statement.setString(2, personID);
			statement.setString(3, activityID);
			statement.setString(4, activitySession);
			rs = statement.executeQuery();
			if(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setVIPID(rs.getInt("VIPID"));
				Person person = new Person();
				person.setID(rs.getInt("person.ID"));
				person.setPetname(rs.getString("petname"));
				person.setSex(rs.getString("sex"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setRealname(rs.getString("realname"));
				person.setResidentID(rs.getString("residentID"));
				person.setBirthday(rs.getDate("birthday"));
				appointment.setPerson(person);
				appointment.setDate(rs.getDate("date"));
				return appointment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}
	
	@Override
	public ArrayList<Appointment> findAppointmentsByVIP(String VIPID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from appointment,person where VIPID = ? and appointment.personID = person.ID");
			statement.setString(1, VIPID);
			rs = statement.executeQuery();
			ArrayList<Appointment> appointments = new ArrayList<Appointment>();
			while(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setVIPID(rs.getInt("VIPID"));
				Person person = new Person();
				person.setID(rs.getInt("person.ID"));
				person.setPetname(rs.getString("petname"));
				person.setSex(rs.getString("sex"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setRealname(rs.getString("realname"));
				person.setResidentID(rs.getString("residentID"));
				person.setBirthday(rs.getDate("birthday"));
				appointment.setPerson(person);
				appointment.setActivityID(rs.getInt("activityID"));
				appointment.setSession(rs.getInt("session"));
				appointment.setDate(rs.getDate("date"));
				appointments.add(appointment);
			}
			return appointments;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}

	@Override
	public ArrayList<Record> findRecordsByVIP(String VIPID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from record,person where VIPID = ? and record.personID = person.ID");
			statement.setString(1, VIPID);
			rs = statement.executeQuery();
			ArrayList<Record> records = new ArrayList<Record>();
			while(rs.next()){
				Record record = new Record();
				record.setVIPID(rs.getInt("VIPID"));
				Person person = new Person();
				person.setID(rs.getInt("person.ID"));
				person.setPetname(rs.getString("petname"));
				person.setSex(rs.getString("sex"));
				person.setAddress(rs.getString("address"));
				person.setTelephone(rs.getString("telephone"));
				person.setEmail(rs.getString("email"));
				person.setRealname(rs.getString("realname"));
				person.setResidentID(rs.getString("residentID"));
				person.setBirthday(rs.getDate("birthday"));
				record.setPerson(person);
				record.setActivityID(rs.getInt("activityID"));
				record.setSession(rs.getInt("session"));
				records.add(record);
			}
			return records;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}
	
	@Override
	public ArrayList<Payment> findPaymentsByVIP(String VIPID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from payment where VIPID = ?");
			statement.setString(1, VIPID);
			rs = statement.executeQuery();
			ArrayList<Payment> payments = new ArrayList<Payment>();
			while(rs.next()){
				Payment payment = new Payment();
				payment.setID(rs.getInt("ID"));
				payment.setVIPID(rs.getInt("VIPID"));
				payment.setDate(rs.getDate("date"));
				payment.setBankCard(rs.getInt("bankcard"));
				payment.setPayment(rs.getInt("payment"));
				payment.setRemark(rs.getString("remark"));
				payments.add(payment);
			}
			return payments;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return null;
	}
	
	@Override
	public void update(VIPPerson VIPperson) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			//更新person
			statement = conn.prepareStatement("update person set petname = ?,age = ?,sex = ?,address = ?,telephone = ?,email = ?,realname = ?,residentID = ?,birthday = ? where ID = ?");
			statement.setString(1, VIPperson.getPerson().getPetname());
			statement.setInt(2, VIPperson.getPerson().getAge());
			statement.setString(3, VIPperson.getPerson().getSex());
			statement.setString(4, VIPperson.getPerson().getAddress());
			statement.setString(5, VIPperson.getPerson().getTelephone());
			statement.setString(6, VIPperson.getPerson().getEmail());
			statement.setString(7, VIPperson.getPerson().getRealname());
			statement.setString(8, VIPperson.getPerson().getResidentID());
			statement.setDate(9, new java.sql.Date(VIPperson.getPerson().getBirthday().getTime()));	
			statement.setInt(10, VIPperson.getPerson().getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			//更新VIPcard
			statement = conn.prepareStatement("update vipcard set balance = ?,state = ? where ID = ?");
			statement.setInt(1,VIPperson.getBalance());
			statement.setString(2, VIPperson.getState());
			statement.setInt(3, VIPperson.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			//更新user
			statement = conn.prepareStatement("update user set username = ?,password = ? where ID = ?");
			statement.setString(1, VIPperson.getUsername());
			statement.setString(2, VIPperson.getPassword());
			statement.setInt(3, VIPperson.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	@Override
	public void update(VIPFamily VIPfamily) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			//更新person
			ArrayList<Person> persons = VIPfamily.getPersons();
			statement = conn.prepareStatement("update person set petname = ?,age = ?,sex = ?,address = ?,telephone = ?,email = ?,realname = ?,residentID = ?,birthday = ? where ID = ?");
			for(Person person : persons){
				statement.setString(1, person.getPetname());
				statement.setInt(2, person.getAge());
				statement.setString(3, person.getSex());
				statement.setString(4, person.getAddress());
				statement.setString(5, person.getTelephone());
				statement.setString(6, person.getEmail());
				statement.setString(7, person.getRealname());
				statement.setString(8, person.getResidentID());
				statement.setDate(9, new java.sql.Date(person.getBirthday().getTime()));	
				statement.setInt(10, person.getID());
				statement.executeUpdate();
			}
			db.closePreparedStatement(statement);
			//更新VIPcard
			statement = conn.prepareStatement("update vipcard set balance = ?,state = ? where ID = ?");
			statement.setInt(1, VIPfamily.getBalance());
			statement.setString(2, VIPfamily.getState());
			statement.setInt(3, VIPfamily.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			//更新user
			statement = conn.prepareStatement("update user set username = ?,password = ? where ID = ?");
			statement.setString(1, VIPfamily.getUsername());
			statement.setString(2, VIPfamily.getPassword());
			statement.setInt(3, VIPfamily.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	@Override
	public void update(Person person) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			//更新person
			statement = conn.prepareStatement("update person set petname = ?,age = ?,sex = ?,address = ?,telephone = ?,email = ?,realname = ?,residentID = ?,birthday = ? where ID = ?");
			statement.setString(1, person.getPetname());
			statement.setInt(2, person.getAge());
			statement.setString(3, person.getSex());
			statement.setString(4, person.getAddress());
			statement.setString(5, person.getTelephone());
			statement.setString(6, person.getEmail());
			statement.setString(7, person.getRealname());
			statement.setString(8, person.getResidentID());
			statement.setDate(9, new java.sql.Date(person.getBirthday().getTime()));	
			statement.setInt(10, person.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

	@Override
	public void remove(Person person) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from vipperson where personID = ? ");	
			statement.setInt(1, person.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

	@Override
	public void remove(Appointment appointment) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from appointment where VIPID = ? and personID = ? and activityID = ? and session = ?");	
			statement.setInt(1, appointment.getVIPID());
			statement.setInt(2, appointment.getPerson().getID());
			statement.setInt(3, appointment.getActivityID());
			statement.setInt(4, appointment.getSession());
			
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

	@Override
	public void removeAppointment(String VIPID, String personID, String activityID,
			String activitySession) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from appointment where VIPID = ? and personID = ? and activityID = ? and session = ?");	
			statement.setString(1, VIPID);
			statement.setString(2, personID);
			statement.setString(3, activityID);
			statement.setString(4, activitySession);
			
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

}
