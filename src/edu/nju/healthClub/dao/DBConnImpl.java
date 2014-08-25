package edu.nju.healthClub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBConnImpl implements DBConnDao{
	
	private InitialContext jndiContext = null;
	private DataSource datasource = null;
	
	public DBConnImpl(){
		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
		"org.apache.naming.java.javaURLContextFactory");

		try {
			jndiContext = new InitialContext(properties);
			datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/healthclub");
		} catch (NamingException e) {
			e.printStackTrace();
		}	
	}
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = datasource.getConnection();
			connection.setAutoCommit(false);
		}catch (SQLException e) {
			e.printStackTrace();
		}
			
		return connection;
	}
	public void closeConnection(Connection conn)
	{
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void rollback(Connection conn) {
		if(conn!=null){
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closePreparedStatement(PreparedStatement statement)
	{
		if(statement!=null){
			try{
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void closeResult(ResultSet rs)
	{
		if(rs!=null){
			try{
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
