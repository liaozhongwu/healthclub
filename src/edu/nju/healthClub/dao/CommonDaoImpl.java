package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Place;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;

public class CommonDaoImpl extends UserDaoImpl implements CommonDao{
	
	@Override
	public void saveAppointment(int personID,int activitySessionID){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into appointment values(?,?,?)");
			statement.setInt(1, personID);
			statement.setInt(2, activitySessionID);
			statement.setLong(3, System.currentTimeMillis());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}
	
	@Override
	public void save(Person person) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try{
			statement = conn.prepareStatement("select * from person where ID = (select max(ID) from person where VIPID = ?)");
			statement.setInt(1, person.getVIP().getID());
			rs = statement.executeQuery();
			int ID;
			if(rs.next()) ID = rs.getInt("ID") + 1;
			else ID = person.getVIP().getID() * 10 + 1;
			person.setID(ID);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into person values(?,?,?,?,?,?,?,?,?)");
			statement.setInt(1, person.getID());
			statement.setInt(2, person.getVIP().getID());
			statement.setString(3, person.getName());
			statement.setString(4, person.getSex());
			statement.setString(5, person.getBirthday());
			statement.setString(6, person.getAddress());
			statement.setString(7, person.getTelephone());
			statement.setString(8, person.getEmail());
			statement.setString(9, person.getResident());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		}catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	
	@Override
	public void update(VIP vip) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("update vip set balance = ?,state = ? where ID = ?");
			statement.setInt(1,vip.getBalance());
			statement.setString(2, vip.getState().name());
			statement.setInt(3, vip.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("update user set username = ?,password = ? where ID = ?");
			statement.setString(1, vip.getUsername());
			statement.setString(2, vip.getPassword());
			statement.setInt(3, vip.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
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
			statement = conn.prepareStatement("update person set name = ?,sex = ?,birthday = ?,address = ?,telephone = ?,email = ?,resident = ? where ID = ?");
			statement.setString(1, person.getName());
			statement.setString(2, person.getSex());
			statement.setString(3, person.getBirthday());	
			statement.setString(4, person.getAddress());
			statement.setString(5, person.getTelephone());
			statement.setString(6, person.getEmail());
			statement.setString(7, person.getResident());
			statement.setInt(8, person.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}
	

	@Override
	public void deletePerson(int personID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from person where ID = ? ");	
			statement.setInt(1, personID);
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

	@Override
	public void deleteAppointment(int personID, int activitySessionID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from appointment where personID = ? and activitysessionID = ?");	
			statement.setInt(1, personID);
			statement.setInt(2, activitySessionID);
			
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

	@Override
	public ArrayList<Appointment> findAppointmentsOfVIP(int VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		try {
			statement = conn.prepareStatement("select * from appointment,person where personID = person.ID and VIPID = ?");
			statement.setInt(1, VIPID);
			rs = statement.executeQuery();
			while(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setPerson(queryPerson(rs.getInt("personID")));
				appointment.setActivitySession(queryActivitySession(rs.getInt("activitysessionID")));
				appointment.setDate(rs.getString("date"));
				appointments.add(appointment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return appointments;
	}

	@Override
	public ArrayList<Record> findRecordsOfVIP(int VIPID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			statement = conn.prepareStatement("select * from record,person where personID = person.ID and VIPID = ?");
			statement.setInt(1, VIPID);
			rs = statement.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setPerson(queryPerson(rs.getInt("personID")));
				record.setActivitySession(queryActivitySession(rs.getInt("activitysessionID")));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return records;
	}
	
	@Override
	public ArrayList<Payment> findPaymentsOfVIP(int VIPID) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Payment> payments = new ArrayList<Payment>();
		try {
			statement = conn.prepareStatement("select * from payment where VIPID = ?");
			statement.setInt(1, VIPID);
			rs = statement.executeQuery();
			while(rs.next()){
				Payment payment = new Payment();
				payment.setID(rs.getInt("ID"));
				payment.setVIP(queryVIP(rs.getInt("VIPID")));
				payment.setDate(rs.getString("date"));
				payment.setBankCard(rs.getInt("bankcard"));
				payment.setPayment(rs.getInt("payment"));
				payment.setRemark(rs.getString("remark"));
				payments.add(payment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return payments;
	}
	@Override
	public ArrayList<Coach> findCoachs(String key) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Coach> coachs = new ArrayList<Coach>();
		try {
			statement = conn.prepareStatement("select * from coach where ID like '%"+ key +"%' or name like '%"+ key +"%' or sex like '%"+ key +"%' or major like '%"+ key +"%' or introduction like '%"+ key +"%'");
			rs = statement.executeQuery();
			while(rs.next()){
				Coach coach = new Coach(rs.getInt("ID"));
				coach.setName(rs.getString("name"));
				coach.setSex(rs.getString("sex"));
				coach.setMajor(rs.getString("major"));
				coach.setIntroduction(rs.getString("introduction"));
				coach.setPortrait(rs.getString("portrait"));
				coachs.add(coach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return coachs;
	}
	@Override
	public ArrayList<Place> findPlaces(String key) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Place> places = new ArrayList<Place>();
		try {
			statement = conn.prepareStatement("select * from place where ID like '%"+ key +"%' or name like '%"+ key +"%'");
			rs = statement.executeQuery();
			while(rs.next()){
				Place place = new Place(rs.getInt("ID"));
				place.setName(rs.getString("name"));
				places.add(place);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return places;
	}
	@Override
	public ArrayList<ActivitySession> findTodayActivitySessions(){
		Date currDate = new Date(System.currentTimeMillis());
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
		try {
			statement = conn.prepareStatement("select * from activitySession where date = ?");
			statement.setString(1, currDate.toString());
			rs = statement.executeQuery();
			ActivitySession activitySession;
			while(rs.next()){
				activitySession = new ActivitySession();
				activitySession.setID(rs.getInt("ID"));
				activitySession.setActivity(queryActivity(rs.getInt("activityID")));
				activitySession.setDate(rs.getString("date"));
				activitySession.setName(rs.getString("name"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(queryPlace(rs.getInt("placeID")));
				activitySession.setCoach(queryCoach(rs.getInt("coachID")));
				sessions.add(activitySession);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return sessions;
	}
	@Override
	public ArrayList<ActivitySession> findTomorrowActivitySessions(){
		Date currDate = new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24);
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
		try {
			statement = conn.prepareStatement("select * from activitySession where date = ?");
			statement.setString(1, currDate.toString());
			rs = statement.executeQuery();
			ActivitySession activitySession;
			while(rs.next()){
				activitySession = new ActivitySession();
				activitySession.setID(rs.getInt("ID"));
				activitySession.setActivity(queryActivity(rs.getInt("activityID")));
				activitySession.setDate(rs.getString("date"));
				activitySession.setName(rs.getString("name"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(queryPlace(rs.getInt("placeID")));
				activitySession.setCoach(queryCoach(rs.getInt("coachID")));
				sessions.add(activitySession);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return sessions;
	}
	@Override
	public ArrayList<ActivitySession> findActivitySessions(String activityID, String name,
			String date, String time, String placeID, String coachID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
		try {
			String sql = "select * from activitysession,activity where activitySession.activityID = activity.ID";
			if(activityID != null && !activityID.equals(""))
				sql += " and activityID = " + activityID;
			if(name != null && !name.equals(""))
				sql += " and activity.name like '%" + name + "%'";
			if(date != null && !date.equals(""))
				sql += " and date = '" + date + "'";
			if(time != null && !time.equals(""))
				sql += " and starttime <= '" + time + "' and endtime >= '" + time + "'";
			if(placeID != null && !placeID.equals(""))
				sql += " and placeID = " + placeID;
			if(coachID != null && !coachID.equals(""))
				sql += " and coachID = " + coachID;
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			ActivitySession activitySession;
			while(rs.next()){
				activitySession = new ActivitySession();
				activitySession.setID(rs.getInt("activitysession.ID"));
				activitySession.setActivity(queryActivity(rs.getInt("activityID")));
				activitySession.setDate(rs.getString("date"));
				activitySession.setName(rs.getString("activitySession.name"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(queryPlace(rs.getInt("placeID")));
				activitySession.setCoach(queryCoach(rs.getInt("coachID")));
				sessions.add(activitySession);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			db.closeConnection(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return sessions;
	}

}
