package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datamanager.DataManager;
import javabean.UserBean;

public class LoginDAO {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	DataManager dm = new DataManager();
	private String DATABASE_NAME = dm.getDATABASE_NAME();
	private String PROPATIES = dm.getPROPATIES();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	
	public UserBean execute(UserBean userBean) {
		Connection conn = null;
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			// クエリを生成する
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String sql = "select * from User where userName = \"" + userName + "\" and password = \"" + password +"\";";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行して結果を取得する
			ResultSet rs = ps.executeQuery(sql);
						
			// 最終ログイン日時を更新する
			// クエリを生成する
			sql = "UPDATE User SET lastLoginDate = CURRENT_TIMESTAMP;";
			
			// クエリを渡す
			ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ps.executeUpdate();
			
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
		} catch(SQLException | ClassNotFoundException e) {
			try {
				conn.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		} finally {
			if(conn != null) {
				try {
					// オートコミットを有効化する
					conn.setAutoCommit(true);
					
					// DB接続を切断する
					conn.close();
				} catch(SQLException e3) {
					e3.printStackTrace();
				}
			}
		}
		
		return userBean;
	}

}
