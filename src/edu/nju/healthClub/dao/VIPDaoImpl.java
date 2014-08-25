package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nju.healthClub.model.Payment;
import edu.nju.healthClub.model.user.VIP;
import edu.nju.healthClub.model.user.VIPState;
import edu.nju.healthClub.model.user.VIPType;

public class VIPDaoImpl extends CommonDaoImpl implements VIPDao{
	
	@Override
	public void save(VIP vip) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			statement = conn.prepareStatement("select * from vip where ID = (select max(ID) from vip where type = ?)");
			statement.setString(1, vip.getType().name());
			rs = statement.executeQuery();
			if(rs.next()){vip.setID(rs.getInt("ID") + 1);}
			else{
				if(vip.getType() == VIPType.SINGLE)	vip.setID(1000001);
				else if(vip.getType() == VIPType.FAMILY) vip.setID(60000001);
			}
			db.closePreparedStatement(statement);
			db.closeResult(rs);
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into user values(?,?,?)");
			statement.setInt(1, vip.getID());
			statement.setString(2, vip.getUsername());
			statement.setString(3, vip.getPassword());
			statement.executeUpdate();
			db.closePreparedStatement(statement);
			statement = conn.prepareStatement("insert into vip values(?,?,?,?,?)");
			statement.setInt(1, vip.getID());
			statement.setString(2, vip.getType().name());
			statement.setInt(3, vip.getBalance());
			statement.setString(4, vip.getRegisterDate());
			statement.setString(5, VIPState.UNACTIVE.name());
			statement.executeUpdate();
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
	public void freeze(int VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vip set state = ? where ID = ?");
			statement.setString(1, VIPState.FREEZE.name());
			statement.setInt(2, VIPID);
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
}
