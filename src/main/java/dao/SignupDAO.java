package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javabean.UserBean;

public class SignupDAO extends DbConnector {
	private Connection conn = null;
	
	public void execute(UserBean userBean) {
		Connection conn = null;
		try {
			conn = dbConnect();
			
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String firstName = userBean.getFirstName();
			String lastName = userBean.getLastName();
			String nickname = userBean.getNickname();
			String sql = "insert user (userName, password, createdDate, firstName, lastName, nickname) "
					+ "values (\"" + userName + "\", \"" + password + "\", CURRENT_TIMESTAMP, \"" 
					+ firstName + "\", \"" + lastName + "\", \"" + nickname + "\");";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			int num = ps.executeUpdate();
			
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
	}

}
