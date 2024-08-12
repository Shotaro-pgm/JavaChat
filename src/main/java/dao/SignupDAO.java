package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datamanager.DataManager;
import javabean.UserBean;

public class SignupDAO {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	DataManager dm = new DataManager();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	public void execute(UserBean userBean) {
		Connection conn = null;
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			// クエリを生成する
			// userBeanが保持するデータを変数に格納する
			String userName = userBean.getUserName();
			String password = userBean.getPassword();
			String firstName = userBean.getFirstName();
			String lastName = userBean.getLastName();
			String nickname = userBean.getNickname();
			String sql = "insert User (userName, password, createdDate, firstName, lastName, nickname) "
					+ "values (\"" + userName + "\", \"" + password + "\", \"" 
					+ firstName + "\", \"" + lastName + "\", \"" + nickname + "\")";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ps.executeUpdate();
			
			// コミットする
			conn.commit();
		} catch(SQLException | ClassNotFoundException e) {
			try {
				conn.rollback();
			} catch(SQLException e2) {
				e2.printStackTrace();
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
		}
	}

}
