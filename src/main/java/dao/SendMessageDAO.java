package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import datamanager.DataManager;

public class SendMessageDAO {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	DataManager dm = new DataManager();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	public int execute(String sender, String recipient, String content) {
		int result = 0;
		Connection conn = null;
		
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// 確認用
			System.out.println("JDBCドライバ読み込み完了");
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			// クエリを生成する
			String sql = "insert into message (content, sender, recipient, sendTime) values "
					+ "(\"" + content + "\", \"" + sender + "\", \"" + recipient + "\", CURRENT_TIMESTAMP);";
			
			// 確認用
			System.out.println(sql);
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			result = ps.executeUpdate();
			
			// コミットする
			conn.commit();
		} catch(SQLException | ClassNotFoundException e) {
			if(conn != null) {
				try {
					conn.rollback();
					System.out.println("メッセージ送信に失敗しました。");
				} catch(SQLException e2) {
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
					e3.printStackTrace();
				}
			}
		}
		
		return result;
	}

}
