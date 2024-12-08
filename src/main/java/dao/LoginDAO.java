package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datamanager.DBConnectInfo;
import javabean.UserBean;

public class LoginDAO extends DBConnectManager {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	public UserBean execute(UserBean userBean) {
		DBConnectInfo dbci = DbConnect();
		Connection conn = dbci.getConn();
		PreparedStatement pstmt = dbci.getPstmt();
		ResultSet rs = dbci.getRs();
		
		try {
			// クエリを生成する
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String sql = "select * from User where userName = \"" + userName + "\" and password = \"" + password +"\";";
			
			// クエリを渡す
			pstmt = conn.prepareStatement(sql);
			
			// クエリを実行して結果を取得する
			rs = pstmt.executeQuery(sql);
						
			// 最終ログイン日時を更新する
			// クエリを生成する
			sql = "UPDATE User SET lastLoginDate = CURRENT_TIMESTAMP;";
			
			// クエリを渡す
			pstmt = conn.prepareStatement(sql);
			
			// クエリを実行する
			pstmt.executeUpdate();
			
			// コミットする
			conn.commit();
			
			// 取得した実行結果をuserBeanに格納する
			while(rs.next()) {
			String id = rs.getString("id");
			userName = rs.getString("userName");
			password = rs.getString("password");
			String createDate = rs.getString("createDate");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String nickname = rs.getString("nickname");
			String greeting = rs.getString("greeting");
			String lastLoginDate = rs.getString("lastLoginDate");
			
			userBean.setId(id);
			userBean.setUserName(userName);
			userBean.setPassword(password);
			userBean.setCreatedDate(createDate);
			userBean.setFirstName(firstName);
			userBean.setLastName(lastName);
			userBean.setNickname(nickname);
			userBean.setGreeting(greeting);
			userBean.setLastLoginDate(lastLoginDate);
			}
		} catch(SQLException e) {
			if(conn != null) {
				try {
					System.out.println("ロールバックしました。");
					conn.rollback();
				} catch(SQLException e2) {
					System.out.println("ロールバックでエラーが発生しました。");
					e2.printStackTrace();
				}
			}
		} finally {
			DbClose(dbci);
		}
		
		return userBean;
	}

}
