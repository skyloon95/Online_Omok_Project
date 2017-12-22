package kr.devdogs.omokServer.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {
	
	private DBConnectionPoolManager connMgr = DBConnectionPoolManager.getInstance();
	private String poolName;
	private String JDBC_DRIVER_NAME;
	private String DB_URL;
	private String USER_ID;
	private String USER_PASSWORD;
	private int MAX_CONN;
	private int INIT_CONN;
	private int MAX_WAIT;
	
	public ConnectionManager(String pool){
		poolName = pool;
		Properties properties = new Properties();
		try{
			InputStream in = ConnectionManager.class.getResourceAsStream("res/DB.properties");
			properties.load(in);
			JDBC_DRIVER_NAME = properties.getProperty("JDBC_DRIVER_NAME");
			DB_URL = properties.getProperty("DB_URL");
			USER_ID = properties.getProperty("USER_ID");
			USER_PASSWORD = properties.getProperty("USER_PASSWORD");
			MAX_CONN = Integer.parseInt(properties.getProperty("MAX_CONN"));
			INIT_CONN = Integer.parseInt(properties.getProperty("INIT_CONN"));
			MAX_WAIT = Integer.parseInt(properties.getProperty("MAX_WAIT"));
		}catch(IOException e){
			e.printStackTrace();
		}
		connMgr.init(poolName, JDBC_DRIVER_NAME, DB_URL, USER_ID, USER_PASSWORD, MAX_CONN, INIT_CONN, MAX_WAIT);
	}
	
	public Connection getConnection(){
		return (connMgr.getConnection(poolName));
	}
	
	public int getDriverNumber(){
		return connMgr.getDriverNumber();
	}
	
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			freeConnection(con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection con, Statement stmt, ResultSet rs) {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			freeConnection(con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
			freeConnection(con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection con, Statement stmt) {
		try {
			if(stmt != null) stmt.close();
			freeConnection(con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void freeConnection(Connection conn){
		connMgr.freeConnection(poolName, conn);
	}
	
}
