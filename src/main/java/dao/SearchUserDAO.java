package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserDAO extends DbConnector {
	public String execute(String recipient){
		String rcpCheck = null;
		Connection conn = null;
		
		try {
			conn = dbConnect();
			
			String sql = "select userName from user where userName = \"" + recipient + "\";";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			while(rs.next()) {
				rcpCheck = rs.getString("userName");
			}
			
			conn.commit();
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return rcpCheck;
	}

}
