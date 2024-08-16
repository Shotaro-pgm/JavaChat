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

public class GetChatHistoryDAO {
	// DB接続に使用する情報
		// データマネージャオブジェクトをインスタンス化する
		DataManager dm = new DataManager();
		private String URL = dm.getURL();
		private String USER = dm.getUSER();
		private String PASSWORD = dm.getPASSWORD();
		private String JDBC_DRIVER = dm.getJDBC_DRIVER();
		
		public List<ChatroomBean> execute(String sender, String recipient){
			// 返却用のリスト
			List<ChatroomBean> chatHistory = new ArrayList();
			Connection conn = null;
			
			try {
				// MySQLに接続する
				Class.forName(JDBC_DRIVER);
				
				// データベースに接続する
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				
				// オートコミットをオフにする
				conn.setAutoCommit(false);
				
				// クエリを生成する
				String sql = "select content, sender from message where (sender = "
						+ "\"" + sender + "\" and recipient = \"" + recipient 
						+ "\") or (sender = \"" + recipient + "\" and recipient = \"" 
						+ sender + "\") order by sendTime desc;";
				
				// クエリを渡す
				PreparedStatement ps = conn.prepareStatement(sql);
				
				// クエリを実行する
				ResultSet rs = null;
				rs = ps.executeQuery();
				
				// 取得結果をリストに格納する
				ChatroomBean chatroomBean = null;
				while(rs.next()) {
					// 取得結果を変数に格納する
					String content = rs.getString("content");
					sender = rs.getString("sender");
					
					// 確認用
					System.out.println(content);
					System.out.println(sender);
					
					/*
					// JavaBeanのパラメータに設定する
					chatroomBean.setContent(content);
					chatroomBean.setSender(sender);
					*/
					
					chatroomBean = new ChatroomBean(sender, content);
					
					System.out.println("JavaBeanにセット後の確認：" + chatroomBean.getContent());
					
					// リストに追加する
					chatHistory.add(chatroomBean);
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
						System.out.println("チャットルーム一覧取得でエラーが発生しました。");
						e3.printStackTrace();
					}
				}
			}
			
			return chatHistory;
		}

}
