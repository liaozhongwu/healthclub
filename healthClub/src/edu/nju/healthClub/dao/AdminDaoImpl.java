package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.Person;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.Waiter;
import edu.nju.healthClub.model.user.vip.VIPFamily;
import edu.nju.healthClub.model.user.vip.VIPPerson;

public class AdminDaoImpl implements AdminDao{
	
	private DBConnImpl db = new DBConnImpl();
	
	@Override
	public void save(Waiter waiter) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from waiter");
			rs = statement.executeQuery();
			if(rs.next()){
				waiter.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, waiter.getID());
			statement.setString(2, waiter.getUsername());
			statement.setString(3, waiter.getPassword());
			statement.setString(4, "waiter");
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into waiter values(?,?)");
			statement.setInt(1, waiter.getID());
			statement.setString(2, waiter.getName());
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
	public void save(Manager manager) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from manager");
			rs = statement.executeQuery();
			if(rs.next()){
				manager.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into manager values(?,?)");
			statement.setInt(1, manager.getID());
			statement.setString(2, manager.getName());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, manager.getID());
			statement.setString(2, manager.getUsername());
			statement.setString(3, manager.getPassword());
			statement.setString(4, "manager");
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
	public void update(Waiter waiter) {
		 
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("update waiter set name = ? where ID = ?");
			statement.setString(1, waiter.getName());
			statement.setInt(2, waiter.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("update user set username = ?,password = ? where ID = ?");
			statement.setString(1, waiter.getUsername());
			statement.setString(2, waiter.getPassword());
			statement.setInt(3, waiter.getID());
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
	public void update(Manager manager) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("update manager set name = ? where ID = ?");
			statement.setString(1, manager.getName());
			statement.setInt(2, manager.getID());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("update user set username = ?,password = ? where ID = ?");
			statement.setString(1, manager.getUsername());
			statement.setString(2, manager.getPassword());
			statement.setInt(3, manager.getID());
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
	public void removeWaiter(String ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from waiter where ID = ?");
			statement.setString(1, ID);
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from user where ID = ?");
			statement.setString(1, ID);
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
	public void removeManager(String ID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from manager where ID = ?");
			statement.setString(1, ID);
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from user where ID = ?");
			statement.setString(1, ID);
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
	public ArrayList<Waiter> getAllWaiter() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from user,waiter where user.type = 'waiter' and user.ID = waiter.ID");
			rs = statement.executeQuery();
			ArrayList<Waiter> waiters = new ArrayList<Waiter>();
			while(rs.next()){
				Waiter waiter = new Waiter();
				waiter.setID(rs.getInt("ID"));
				waiter.setUsername(rs.getString("username"));
				waiter.setPassword(rs.getString("password"));
				waiter.setName(rs.getString("name"));
				waiters.add(waiter);
			}
			return waiters;
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
	public ArrayList<Manager> getAllManager() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from user,manager where user.type = 'manager' and user.ID = manager.ID");
			rs = statement.executeQuery();
			ArrayList<Manager> managers = new ArrayList<Manager>();
			while(rs.next()){
				Manager manager = new Manager();
				manager.setID(rs.getInt("ID"));
				manager.setUsername(rs.getString("username"));
				manager.setPassword(rs.getString("password"));
				manager.setName(rs.getString("name"));
				managers.add(manager);
			}
			return managers;
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
	public int findNoPayment(String VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from nopayment where VIPID = ?");
			statement.setString(1, VIPID);
			rs = statement.executeQuery();
			if(rs.next()){
				return rs.getInt("month");
			}else{
				return 0;
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
		return 0;
	}

	@Override
	public void saveNoPayment(String VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from nopayment where VIPID = ?");
			statement.setString(1, VIPID);
			rs = statement.executeQuery();
			int month = 1;
			if(rs.next()){
				month = rs.getInt("month") + 1;
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				statement = conn.prepareStatement("update nopayment set month = ? where VIPID = ?");
				statement.setInt(1, month);
				statement.setString(2, VIPID);
				statement.executeUpdate();
			}else{
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				statement = conn.prepareStatement("insert into nopayment values(?,?)");
				statement.setString(1, VIPID);
				statement.setInt(2, month);
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
	public void save(Payment payment) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from payment");
			rs = statement.executeQuery();
			if(rs.next()){
				payment.setID(rs.getInt("max(ID)") + 1);
			}
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into payment values(?,?,?,?,?,?)");
			statement.setInt(1,payment.getID());
			statement.setInt(2, payment.getVIPID());
			statement.setDate(3,new java.sql.Date(payment.getDate().getTime()));
			statement.setInt(4, payment.getBankCard());
			statement.setInt(5, payment.getPayment());
			statement.setString(6, payment.getRemark());
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
	public void updateState(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vipcard set state = ? where ID = ?");
			statement.setString(1, vip.getState());
			statement.setInt(2, vip.getID());
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
	public void updateBalance(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vipcard set balance = ? where ID = ?");
			statement.setInt(1, vip.getBalance());
			statement.setInt(2, vip.getID());
			statement.executeUpdate();
			
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
	public void updateVIPState(String VIPID, String state) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vipcard set state = ? where ID = ?");
			statement.setString(1, state);
			statement.setString(2, VIPID);
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
