package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javabean.UserBean;
import util.LogExporter;

public class LoginDAO extends DBConnctor {
	private LogExporter logExporter = new LogExporter();
	
	public UserBean execute(UserBean userBean) {
		Connection conn = null;
		try {
			conn = DbConnect();
			
			// クエリを生成する
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String sql = "select * from account where userName = \'" + userName + "\' and password = \'" + password +"\';";
			logExporter.runLog(sql);
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行して結果を取得する
			ResultSet rs = ps.executeQuery(sql);
						
			// 最終ログイン日時を更新する
			// クエリを生成する
			sql = "UPDATE account SET lastLoginDate = CURRENT_TIMESTAMP where userName = \'" + userName + "\' and password = \'" + password +"\';";
			
			// クエリを渡す
			ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ps.executeUpdate();
			
			// 取得した実行結果をuserBeanに格納する
			while(rs.next()) {
			String id = rs.getString("id");
			userName = rs.getString("userName");
			password = rs.getString("password");
			String createDate = rs.getString("createdDate");
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
					conn.rollback();
					System.out.println("ロールバックしました。");
					e.printStackTrace();
				} catch(SQLException e2) {
					System.out.println("ロールバックでエラーが発生しました。");
					e2.printStackTrace();
				}
			}
		} finally {
			DBClose(conn);
		}
		
		return userBean;
	}

}
