package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.Manager;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;
import edu.nju.healthClub.model.user.Waiter;

public class AdminDaoImpl extends UserDaoImpl implements AdminDao{
	
	@Override
	public void save(Waiter waiter) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from waiter where ID = (select max(ID) from waiter)");
			rs = statement.executeQuery();
			if(rs.next()){waiter.setID(rs.getInt("ID") + 1);}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, waiter.getID());
			statement.setString(2, waiter.getUsername());
			statement.setString(3, waiter.getPassword());
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
			db.rollback(conn);
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
			statement = conn.prepareStatement("select * from manager where ID = (select max(ID) from manager)");
			rs = statement.executeQuery();
			if(rs.next()){manager.setID(rs.getInt("ID") + 1);}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			statement = conn.prepareStatement("insert into user values(?,?,?,?)");
			statement.setInt(1, manager.getID());
			statement.setString(2, manager.getUsername());
			statement.setString(3, manager.getPassword());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into manager values(?,?)");
			statement.setInt(1, manager.getID());
			statement.setString(2, manager.getName());
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
			db.rollback(conn);
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
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
	}

	@Override
	public void deleteWaiter(int waiterID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from user where ID = ?");
			statement.setInt(1, waiterID);
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from waiter where ID = ?");
			statement.setInt(1, waiterID);
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
	public void deleteManager(int managerID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("delete from user where ID = ?");
			statement.setInt(1, managerID);
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("delete from manager where ID = ?");
			statement.setInt(1, managerID);
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
	public ArrayList<Waiter> getAllWaiter() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Waiter> waiters = new ArrayList<Waiter>();
		try {
			statement = conn.prepareStatement("select * from user,waiter where user.ID = waiter.ID");
			rs = statement.executeQuery();
			while(rs.next()){
				Waiter waiter = new Waiter();
				waiter.setID(rs.getInt("ID"));
				waiter.setUsername(rs.getString("username"));
				waiter.setPassword(rs.getString("password"));
				waiter.setName(rs.getString("name"));
				waiters.add(waiter);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return waiters;
	}
	
	@Override
	public ArrayList<Manager> getAllManager() {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		ArrayList<Manager> managers = new ArrayList<Manager>();
		try {
			statement = conn.prepareStatement("select * from user,manager where user.ID = manager.ID");
			rs = statement.executeQuery();
			while(rs.next()){
				Manager manager = new Manager();
				manager.setID(rs.getInt("ID"));
				manager.setUsername(rs.getString("username"));
				manager.setPassword(rs.getString("password"));
				manager.setName(rs.getString("name"));
				managers.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return managers;
	}

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
	public int searchNoPayment(int VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		int month = 0;
		try {
			statement = conn.prepareStatement("select * from nopayment where VIPID = ?");
			statement.setInt(1, VIPID);
			rs = statement.executeQuery();
			if(rs.next()){month =  rs.getInt("month");}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
			db.closeResult(rs);
		}
		return month;
	}

	@Override
	public void saveNoPayment(int VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from nopayment where VIPID = ?");
			statement.setInt(1, VIPID);
			rs = statement.executeQuery();
			int month = 1;
			if(rs.next()){
				month = rs.getInt("month") + 1;
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				statement = conn.prepareStatement("update nopayment set month = ? where VIPID = ?");
				statement.setInt(1, month);
				statement.setInt(2, VIPID);
				statement.executeUpdate();
			}else{
				db.closePreparedStatement(statement);
				db.closeResult(rs);
				statement = conn.prepareStatement("insert into nopayment values(?,?)");
				statement.setInt(1, VIPID);
				statement.setInt(2, month);
				statement.executeUpdate();
			}
			
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
	public void save(Payment payment) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select max(ID) from payment");
			rs = statement.executeQuery();
			if(rs.next()){payment.setID(rs.getInt("max(ID)") + 1);}
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into payment values(?,?,?,?,?,?)");
			statement.setInt(1,payment.getID());
			statement.setInt(2, payment.getVIP().getID());
			statement.setString(3, payment.getDate());
			statement.setInt(4, payment.getBankCard());
			statement.setInt(5, payment.getPayment());
			statement.setString(6, payment.getRemark());
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
	public void updateState(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vip set state = ? where ID = ?");
			statement.setString(1, vip.getState().name());
			statement.setInt(2, vip.getID());
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
	public void updateBalance(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vip set balance = ? where ID = ?");
			statement.setInt(1, vip.getBalance());
			statement.setInt(2, vip.getID());
			statement.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			db.rollback(conn);
		} finally{
			db.closeConnection(conn);
			db.closePreparedStatement(statement);
		}
	}

}
