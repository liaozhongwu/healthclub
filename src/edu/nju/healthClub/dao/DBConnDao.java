package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DBConnDao {
	public Connection getConnection();
	public void rollback(Connection conn);
	public void closeConnection(Connection conn);
	public void closePreparedStatement(PreparedStatement stmt);	
	public void closeResult(ResultSet result);
}
