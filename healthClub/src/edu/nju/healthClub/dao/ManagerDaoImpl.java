package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Coach;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public class ManagerDaoImpl implements ManagerDao{
	private DBConnImpl db = new DBConnImpl();
	
	@Override
	public ArrayList<VIP> getAllVIP() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from vipcard");
			rs = statement.executeQuery();
			ArrayList<VIP> VIPs = new ArrayList<VIP>();
			VIP vip;
			while(rs.next()){
				int ID = rs.getInt("ID");
				String type = rs.getString("type");
				int balance = rs.getInt("balance");
				String state = rs.getString("state");
				if(type.equals("person")){
					vip = new VIPPerson();
					vip.setID(ID);
					vip.setBalance(balance);
					vip.setState(state);
				}else{
					vip = new VIPFamily();
					vip.setID(ID);
					vip.setBalance(balance);
					vip.setState(state);
				}
				VIPs.add(vip);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			for(VIP temp : VIPs){
				if(temp instanceof VIPPerson){
					statement = conn.prepareStatement("select * from vipperson,person where vipperson.VIPID = ? and vipperson.personID = person.ID");
					statement.setInt(1, temp.getID());
					rs = statement.executeQuery();
					if(rs.next()){
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
						((VIPPerson) temp).setPerson(person);
					}
				}else if(temp instanceof VIPFamily){
					statement = conn.prepareStatement("select * from vipperson,person where vipperson.VIPID = ? and vipperson.personID = person.ID");
					statement.setInt(1, temp.getID());
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
					((VIPFamily) temp).setPersons(persons);		
				}
			}
			return VIPs;
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
	public ArrayList<Record> getAllRecord() {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from record,person where record.personID = person.ID");
			rs = statement.executeQuery();
			ArrayList<Record> records = new ArrayList<Record>();
			while(rs.next()){
				Record record = new Record();
				record.setActivityID(rs.getInt("activityID"));
				record.setSession(rs.getInt("session"));
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
	public ArrayList<ActivitySession> getAllActivitySession() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from activitySession");
			rs = statement.executeQuery();
			ActivitySession activitySession;
			ArrayList<ActivitySession> sessions = new ArrayList<ActivitySession>();
			while(rs.next()){
				activitySession = new ActivitySession();
				activitySession.setActivityID(rs.getInt("activityID"));
				activitySession.setSession(rs.getInt("session"));
				activitySession.setDate(rs.getDate("date"));
				activitySession.setStarttime(rs.getString("starttime"));
				activitySession.setEndtime(rs.getString("endtime"));
				activitySession.setPlace(rs.getString("place"));
				sessions.add(activitySession);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			for(ActivitySession session : sessions){
				statement = conn.prepareStatement("select * from activitycoach,coach where activityID = ? and session = ? and activitycoach.coachID = coach.ID");
				statement.setInt(1, session.getActivityID());
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
			
			return sessions;
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
}
