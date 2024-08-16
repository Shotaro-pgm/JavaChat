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
		/* GetChatHistoryDAOで利用
		// 返却用のリスト
		List<ChatroomBean> chatHistory = new ArrayList();
		*/
		String rcpCheck = null;
		Connection conn = null;
		
		try {
			// MySQLに接続する
			Class.forName(JDBC_DRIVER);
			
			// データベースに接続する
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			
			// オートコミットをオフにする
			conn.setAutoCommit(false);
			
			/* GetChatHistoryDAOで利用
			// クエリを生成する
			String sql = "select content, sender from message where (sender = "
					+ "\"" + sender + "\" and recipient = \"" + recipient 
					+ "\") or (sender = \"" + recipient + "\" and recipient = \"" 
					+ sender + "\") order by sendTime desc;";
			*/
			String sql = "select userName from user where userName = \"" + recipient + "\";";
			
			// クエリを渡す
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// クエリを実行する
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			/* GetChatHistoryDAOで利用
			// 取得結果をリストに格納する
			ChatroomBean chatroomBean = new ChatroomBean();
			while(rs.next()) {
				// 取得結果を変数に格納する
				String content = rs.getString("content");
				sender = rs.getString("sender");
				
				// JavaBeanのパラメータに設定する
				chatroomBean.setContent(content);
				chatroomBean.setSender(sender);
				
				// リストに追加する
				chatHistory.add(chatroomBean);
			}
			*/
			
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
		
		/* GetChatHistoryDAOで利用
		return chatHistory;
		*/
	}

}
