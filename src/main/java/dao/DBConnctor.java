package dao;

import java.sql.Connection;
import java.sql.DriverManager;

import datamanager.DataManager;

public class DBConnctor {
	Connection conn = null;
	DataManager dm = new DataManager();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	public Connection DbConnect() {
		try {
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			conn.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public void DBClose(Connection conn) {
		if(conn != null) {
			try {
				conn.commit();
				
				conn.setAutoCommit(true);
				
				conn.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
