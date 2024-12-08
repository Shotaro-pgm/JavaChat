package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SendMessageDAO extends DbConnector {
	private Connection conn = null;
	
	public int execute(String sender, String recipient, String content) {
		int result = 0;
		
		try {
			conn = dbConnect();
			
			String sql = "insert into message (content, sender, recipient, sendTime) values "
					+ "(\"" + content + "\", \"" + sender + "\", \"" + recipient + "\", CURRENT_TIMESTAMP);";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			result = ps.executeUpdate();
			
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
		
		return result;
	}

}
