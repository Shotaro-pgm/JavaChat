package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javabean.UserBean;

public class SignupDAO extends DBConnctor {
	public void execute(UserBean userBean) {
		Connection conn = null;
		try {
			conn = DbConnect();
			
			// クエリを生成する
			// userBeanが保持するデータを変数に格納する
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String firstName = userBean.getFirstName();
			String lastName = userBean.getLastName();
			String nickname = userBean.getNickname();
			String sql = "insert account (userName, password, createdDate, firstName, lastName, nickname) "
					+ "values (\'" + userName + "\', \'" + password + "\', CURRENT_TIMESTAMP, \'"
					+ firstName + "\', \'" + lastName + "\', \'" + nickname + "\');";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			int num = ps.executeUpdate();
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
					System.out.println("ロールバックしました。");
					e.printStackTrace();
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		}finally {
			DBClose(conn);
		}
	}

}
