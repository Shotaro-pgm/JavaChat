package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datamanager.DBConnectInfo;

public class DBConnectManager {
	
	public DBConnectInfo DbConnect() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DBConnectInfo dbci = new DBConnectInfo();
		
		try {
			conn = DBConnectionPool.getDataSource().getConnection();
			conn.setAutoCommit(false);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		dbci.setConn(conn);
		dbci.setPstmt(pstmt);
		dbci.setRs(rs);
		
		return dbci;
	}
	
	public void DbClose(DBConnectInfo dbci) {
		try {
			if(dbci.getConn() != null) {
				dbci.getConn().close();
			}
			
			if(dbci.getPstmt() != null) {
				dbci.getPstmt().close();
			}
			
			if(dbci.getRs() != null) {
				dbci.getRs().close();
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
