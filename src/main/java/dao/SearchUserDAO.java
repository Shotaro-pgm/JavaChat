package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import datamanager.DataManager;

public class SearchUserDAO {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	DataManager dm = new DataManager();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	public String execute(String recipient){
		String rcpCheck = null;
		Connection conn = null;
		
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			String sql = "select userName from user where userName = \"" + recipient + "\";";
			
			// 確認用
			System.out.println(sql);
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			// 取得結果を変数に格納する
			while(rs.next()) {
				rcpCheck = rs.getString("userName");
			}
			
			// コミットする
			conn.commit();
		} catch(SQLException | ClassNotFoundException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch(SQLException e2) {
					System.out.println("ユーザ情報取得でエラーが発生しました。");
					e2.printStackTrace();
				}
			}
		} finally {
			if(conn != null) {
				try {
					// オートコミットを有効化する
					conn.setAutoCommit(true);
					
					// DB接続を切断する
					conn.close();
				} catch(SQLException e3) {
					System.out.println("ユーザ情報取得でエラーが発生しました。");
					e3.printStackTrace();
				}
			}
		}
		
		return rcpCheck;
	}

}
