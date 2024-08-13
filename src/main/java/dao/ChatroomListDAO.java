package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import datamanager.DataManager;
import javabean.ChatroomBean;

public class ChatroomListDAO {
	// DB接続に使用する情報
	// データマネージャオブジェクトをインスタンス化する
	DataManager dm = new DataManager();
	private String URL = dm.getURL();
	private String USER = dm.getUSER();
	private String PASSWORD = dm.getPASSWORD();
	private String JDBC_DRIVER = dm.getJDBC_DRIVER();
	
	public List<ChatroomBean> execute(String userName){
		// 結果返却用のリストをインスタンス化する
		List<ChatroomBean> list = new ArrayList();
		
		// コネクションオブジェクトをインスタンス化する
		Connection conn = null;
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			// クエリを生成する
			String sql = "select distinct recipient from message where sender = \"" + userName + "\" order by sendTime desc;";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			// 取得結果をリストに格納する
			ChatroomBean chatroomBean = new ChatroomBean();
			while(rs.next()) {
				chatroomBean.setRecipient(rs.getString("recipient"));
				list.add(chatroomBean);
			}
			
			// コミットする
			conn.commit();
		} catch(SQLException | ClassNotFoundException e) {
			if(conn != null) {
				try {
					conn.rollback();
				} catch(SQLException e2) {
					System.out.println("チャットルーム一覧取得でエラーが発生しました。");
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
					System.out.println("チャットルーム一覧取得でエラーが発生しました。");
					e3.printStackTrace();
				}
			}
		}
		
		return list;
	}

}
