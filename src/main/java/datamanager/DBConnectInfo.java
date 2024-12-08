package datamanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnectInfo {
	private Connection conn;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public PreparedStatement getPstmt() {
		return pstmt;
	}
	public void setPstmt(PreparedStatement pstmt) {
		this.pstmt = pstmt;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}

}
