package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.Activity;
import edu.nju.healthClub.model.ActivitySession;
import edu.nju.healthClub.model.Appointment;
import edu.nju.healthClub.model.Record;
import edu.nju.healthClub.model.user.Person;

public class WaiterDaoImpl extends CommonDaoImpl implements WaiterDao{
	
	@Override
	public void save(Activity activity){
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from activity");
			rs = statement.executeQuery();
			if(rs.next()){activity.setID(rs.getInt("max(ID)") + 1);}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into activity values(?,?,?)");
			statement.setInt(1, activity.getID());
			statement.setString(2, activity.getName());
			statement.setString(3, activity.getIntroduction());
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
	public void save(ActivitySession activitySession){
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from activitysession where ID = (select max(ID) from activitysession where activityID = ?)");
			statement.setInt(1, activitySession.getActivity().getID());
			rs = statement.executeQuery();
			int ID;
			if(rs.next()) ID = rs.getInt("ID") + 1;
			else ID = activitySession.getActivity().getID() * 1000 + 1;
			activitySession.setID(ID);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into activitysession values(?,?,?,?,?,?,?,?)");
			statement.setInt(1, activitySession.getID());
			statement.setInt(2, activitySession.getActivity().getID());
			statement.setString(3, activitySession.getName());
			statement.setString(4, activitySession.getDate());
			statement.setString(5, activitySession.getStarttime());
			statement.setString(6, activitySession.getEndtime());
			statement.setInt(7, activitySession.getPlace().getID());
			statement.setInt(8, activitySession.getCoach().getID());
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
	public void saveRecord(int personID,int activitySessionID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("insert into record values(?,?)");
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
	public ArrayList<Appointment> findAppointmentsOfActivitySession(ActivitySession activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		try {
			statement = conn.prepareStatement("select * from appointment where activitysessionID = ?");
			statement.setInt(1, activitySession.getID());
			rs = statement.executeQuery();
			while(rs.next()){
				Appointment appointment = new Appointment();
				appointment.setPerson(queryPerson(rs.getInt("personID")));
				appointment.setActivitySession(activitySession);
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
	public ArrayList<Record> findRecordsOfActivitySession(ActivitySession activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Record> records = new ArrayList<Record>();
		try {
			statement = conn.prepareStatement("select * from record where activitySessionID = ?");
			statement.setInt(1, activitySession.getID());
			rs = statement.executeQuery();
			while(rs.next()){
				Record record = new Record();
				record.setPerson(queryPerson(rs.getInt("personID")));
				record.setActivitySession(activitySession);
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
	public void update(Activity activity) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {

			statement = conn.prepareStatement("update activity set name = ?,introduction = ? where ID = ?");
			statement.setString(1, activity.getName());
			statement.setString(2, activity.getIntroduction());
			statement.setInt(3, activity.getID());
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
	public void update(ActivitySession activitySession) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("update activitySession set name = ?,date = ?,starttime = ?,endtime = ?,placeID = ?,coachID = ? where ID = ?");	
			statement.setString(1, activitySession.getName());
			statement.setString(2, activitySession.getDate());
			statement.setString(3, activitySession.getStarttime());
			statement.setString(4, activitySession.getEndtime());
			statement.setInt(5, activitySession.getPlace().getID());
			statement.setInt(6, activitySession.getCoach().getID());
			statement.setInt(7, activitySession.getID());
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
	public void deleteActivitySession(int activitySessionID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from activitysession where ID = ?");	
			statement.setInt(1, activitySessionID);
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
	public void deleteRecord(int personID,int activitySessionID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from record where personID = ? and activitysessionID = ?");	
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
	public ArrayList<Person> findPersons(String VIPID, String username,
			String registerDate, String name, String telephone, String email) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Person> persons = new ArrayList<Person>();
		try {
			String sql = "select * from person,vip,user where person.VIPID = vip.ID and vip.ID = user.ID";
			if(VIPID != null && !VIPID.equals(""))
				sql += " and VIPID = " + VIPID;
			if(username != null && !username.equals(""))
				sql += " and user.username like '%" + name + "%'";
			if(registerDate != null && !registerDate.equals(""))
				sql += " and vip.registerDate = '" + registerDate + "'";
			if(name != null && !name.equals(""))
				sql += " and person.name = '" + name + "'";
			if(telephone != null && !telephone.equals(""))
				sql += " and telephone = " + telephone;
			if(email != null && !email.equals(""))
				sql += " and email = " + email;
			statement = conn.prepareStatement(sql);
			rs = statement.executeQuery();
			Person person;
			while(rs.next()){
				person = queryPerson(rs.getInt("person.ID"));
				persons.add(person);
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
		return persons;
	}

}
