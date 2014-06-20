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
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.Record;

public class WaiterDaoImpl extends UserDaoImpl implements WaiterDao{
	private DBConnImpl db = new DBConnImpl();
	
	@Override
	public void save(Activity activity){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from activity");
			rs = statement.executeQuery();
			if(rs.next()){
				activity.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into activity values(?,?,?)");
			statement.setInt(1, activity.getID());
			statement.setString(2, activity.getName());
			statement.setString(3, activity.getIntroduction());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			ArrayList<ActivitySession> sessions = activity.getSessions();
			statement = conn.prepareStatement("insert into activitysession values(?,?,?,?,?,?)");
			for(ActivitySession session : sessions){
				statement.setInt(1,activity.getID());
				statement.setInt(2, session.getSession());
				statement.setDate(3, new java.sql.Date(session.getDate().getTime()));
				statement.setString(4, session.getStarttime());
				statement.setString(5, session.getEndtime());
				statement.setString(6, session.getPlace());
				statement.executeUpdate();
			}
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into activitycoach values(?,?,?)");
			for(ActivitySession session : sessions){
				for(Coach coach : session.getCoachs()){
					statement.setInt(1, activity.getID());
					statement.setInt(2, session.getSession());
					statement.setInt(3, coach.getID());
					statement.executeUpdate();
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
	}

	@Override	
	public void save(ActivitySession session){
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("insert into activitysession values(?,?,?,?,?,?)");
			statement.setInt(1,session.getActivityID());
			statement.setInt(2, session.getSession());
			statement.setDate(3, new java.sql.Date(session.getDate().getTime()));
			statement.setString(4, session.getStarttime());
			statement.setString(5, session.getEndtime());
			statement.setString(6, session.getPlace());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into activitycoach values(?,?,?)");
			for(Coach coach : session.getCoachs()){
				statement.setInt(1, session.getActivityID());
				statement.setInt(2, session.getSession());
				statement.setInt(3, coach.getID());
				statement.executeUpdate();
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
	}

	@Override
	public void saveRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into record values(?,?,?,?)");
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
	
	@Override
	public void save(Record record) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into record values(?,?,?,?)");
			statement.setInt(1, record.getVIPID());
			statement.setInt(2, record.getPerson().getID());
			statement.setInt(3, record.getActivityID());
			statement.setInt(4, record.getSession());
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
	public Coach findCoach(String col, String value) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from coach where "+col+" = ?");
			statement.setString(1, value);
			rs = statement.executeQuery();
			if(rs.next()){
				Coach coach = new Coach();
				coach.setID(rs.getInt("ID"));
				coach.setName(rs.getString("name"));
				coach.setSex(rs.getString("sex"));
				coach.setMajor(rs.getString("major"));
				coach.setIntroduction(rs.getString("introduction"));
				coach.setPortrait(rs.getString("portrait"));
				return coach;
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
	public ArrayList<Coach> findCoachs(String key) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from coach where ID like '%" + key + "%' or name like '%" + key + "%' or sex like '%" + key + "%' or major like '%" + key + "%' or introduction like '%" + key + "%'");
			rs = statement.executeQuery();
			ArrayList<Coach> coachs = new ArrayList<Coach>();
			while(rs.next()){
				Coach coach = new Coach();
				coach.setID(rs.getInt("ID"));
				coach.setName(rs.getString("name"));
				coach.setSex(rs.getString("sex"));
				coach.setMajor(rs.getString("major"));
				coach.setIntroduction(rs.getString("introduction"));
				coach.setPortrait(rs.getString("portrait"));
				coachs.add(coach);
			}
			return coachs;
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
	public ArrayList<Appointment> findAppointmentsByActivity(String activityID,String activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from appointment,person where activityID = ? and session = ? and appointment.personID = person.ID");
			statement.setString(1, activityID);
			statement.setString(2, activitySession);
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
				appointment.setDate(rs.getDate("date"));
				appointment.setActivityID(rs.getInt("activityID"));
				appointment.setSession(rs.getInt("session"));
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
	public Record findRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from record,person where VIPID = ? and personID = ? and activityID = ? and session = ? and record.personID = person.ID");
			statement.setString(1, VIPID);
			statement.setString(2, personID);
			statement.setString(3, activityID);
			statement.setString(4, activitySession);
			rs = statement.executeQuery();
			if(rs.next()){
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
				return record;
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
	public ArrayList<Record> findRecordsByActivity(String activityID,
			String session) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from record,person where activityID = ? and session = ? and record.personID = person.ID");
			statement.setString(1, activityID);
			statement.setString(2, session);
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
	public void update(Activity activity) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from activitysession where activityID = ? ");	
			statement.setInt(1, activity.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from activitycoach where activityID = ? ");	
			statement.setInt(1, activity.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			conn.commit();

			statement = conn.prepareStatement("update activity set name = ?,introduction = ? where ID = ?");
			statement.setString(1, activity.getName());
			statement.setString(2, activity.getIntroduction());
			statement.setInt(3, activity.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			ArrayList<ActivitySession> sessions = activity.getSessions();
			statement = conn.prepareStatement("insert into activitysession values(?,?,?,?,?,?)");
			for(ActivitySession session : sessions){
				statement.setInt(1,activity.getID());
				statement.setInt(2, session.getSession());
				statement.setDate(3, new java.sql.Date(session.getDate().getTime()));
				statement.setString(4, session.getStarttime());
				statement.setString(5, session.getEndtime());
				statement.setString(6, session.getPlace());
				statement.executeUpdate();
			}
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into activitycoach values(?,?,?)");
			for(ActivitySession session : sessions){
				for(Coach coach : session.getCoachs()){
					statement.setInt(1, activity.getID());
					statement.setInt(2, session.getSession());
					statement.setInt(3, coach.getID());
					statement.executeUpdate();
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
		}
	}
	
	@Override
	public void remove(ActivitySession activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from activitysession where activityID = ? and session = ?");	
			statement.setInt(1, activitySession.getActivityID());
			statement.setInt(2, activitySession.getSession());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from activitycoach where activityID = ? and session = ?");	
			statement.setInt(1, activitySession.getActivityID());
			statement.setInt(2, activitySession.getSession());
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
	public void remove(Record record) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from record where VIPID = ? and personID = ? and activityID = ? and session = ?");	
			statement.setInt(1, record.getVIPID());
			statement.setInt(2, record.getPerson().getID());
			statement.setInt(3, record.getActivityID());
			statement.setInt(4, record.getSession());
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
	public void removeRecord(String VIPID, String personID, String activityID,
			String activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from record where VIPID = ? and personID = ? and activityID = ? and session = ?");	
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
