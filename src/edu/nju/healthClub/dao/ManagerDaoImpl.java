package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;

public class ManagerDaoImpl extends UserDaoImpl implements ManagerDao{
	
	@Override
	public ArrayList<VIP> getAllVIP() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<VIP> VIPs = new ArrayList<VIP>();
		try {
			statement = conn.prepareStatement("select * from user,vip where user.ID = vip.ID");
			rs = statement.executeQuery();
			VIP vip;
			while(rs.next()){
				vip = new VIP();
				vip.setID(rs.getInt("ID"));
				vip.setUsername(rs.getString("username"));
				vip.setPassword(rs.getString("password"));
				vip.setType(VIPType.valueOf(rs.getString("type")));
				vip.setBalance(rs.getInt("balance"));
				vip.setRegisterDate(rs.getString("registerDate"));
				vip.setState(VIPState.valueOf(rs.getString("state")));
				VIPs.add(vip);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return VIPs;
	}

	@Override
	public ArrayList<Person> getAllPerson() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		try {
			statement = conn.prepareStatement("select * from person");
			rs = statement.executeQuery();
			while(rs.next()){
				Person person = new Person();
				person.setID(rs.getInt("ID"));
				person.setVIP(queryVIP(rs.getInt("VIPID")));
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
	public ArrayList<Record> getAllRecord() {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			statement = conn.prepareStatement("select * from record");
			rs = statement.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setPerson(queryPerson(rs.getInt("personID")));
				record.setActivitySession(queryActivitySession(rs.getInt("activitysessionID")));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return records;
	}
	
	@Override
	public ArrayList<ActivitySession> getAllActivitySession() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
		try {
			statement = conn.prepareStatement("select * from activitySession");
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
				activitySession.setPlace(queryPlace(rs.getInt("place")));
				activitySession.setCoach(queryCoach(rs.getInt("coach")));
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
