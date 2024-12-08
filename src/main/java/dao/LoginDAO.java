package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javabean.UserBean;

public class LoginDAO extends DbConnector {
	private Connection conn = null;	
	
	public UserBean execute(UserBean userBean) {
		try {
			conn = dbConnect();
			
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String sql = "select * from User where userName = \"" + userName + "\" and password = \"" + password +"\";";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery(sql);
			
			sql = "UPDATE User SET lastLoginDate = CURRENT_TIMESTAMP;";
			
			ps = conn.prepareStatement(sql);
			
			ps.executeUpdate();
			
			conn.commit();
			
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
					conn.rollback();
				} catch(SQLException e2) {
					e2.printStackTrace();
				}
			}
		}
		
		return userBean;
	}

}
