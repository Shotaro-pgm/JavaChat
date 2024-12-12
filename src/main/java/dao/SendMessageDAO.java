package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SendMessageDAO extends DBConnctor {
	public int execute(String sender, String recipient, String content) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = DbConnect();
			
			// クエリを生成する
			String sql = "insert into message (content, sender, recipient, sendTime) values "
					+ "(\"" + content + "\", \"" + sender + "\", \"" + recipient + "\", CURRENT_TIMESTAMP);";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			result = ps.executeUpdate();
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
					System.out.println("メッセージ送信に失敗しました。");
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		} finally {
			DBClose(conn);
		}
		
		return result;
	}

}
