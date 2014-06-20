package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nju.healthClub.model.Payment;

public class VIPDaoImpl extends UserDaoImpl implements VIPDao{
	private DBConnImpl db = new DBConnImpl();
	
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
	public void freeze(String VIPID) {
		Connection conn = db.getConnection();
		PreparedStatement statement = null;
		try {
			statement = conn.prepareStatement("update vipcard set state = ? where ID = ?");
			statement.setString(1, "freezing");
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
