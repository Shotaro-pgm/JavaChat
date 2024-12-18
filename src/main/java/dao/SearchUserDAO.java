package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchUserDAO extends DBConnctor {
	public String execute(String recipient){
		String rcpCheck = null;
		Connection conn = null;
		
		try {
			conn = DbConnect();
			
			String sql = "select userName from account where userName = \'" + recipient + "\';";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			// 取得結果を変数に格納する
			while(rs.next()) {
				rcpCheck = rs.getString("userName");
			}
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
					e.printStackTrace();
				} catch(SQLException e2) {
					System.out.println("ユーザ情報取得でエラーが発生しました。");
					e2.printStackTrace();
				}
			}
		} finally {
			DBClose(conn);
		}
		
		return rcpCheck;
	}

}
