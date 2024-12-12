package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ChatroomBean;

public class GetChatHistoryDAO extends DBConnctor {
	public List<ChatroomBean> execute(String sender, String recipient){
		// 返却用のリスト
		List<ChatroomBean> chatHistory = new ArrayList();
		Connection conn = null;
		
		try {
			conn = DbConnect();
			
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
				
				chatroomBean = new ChatroomBean(sender, content);
				
				System.out.println("JavaBeanにセット後の確認：" + chatroomBean.getContent());
				
				// リストに追加する
				chatHistory.add(chatroomBean);
			}
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
					e.printStackTrace();
				} catch(SQLException e2) {
					System.out.println("ユーザ情報取得でエラーが発生しました。");
					e2.printStackTrace();
				}
			}
		} finally {
			DBClose(conn);
		}
		
		return chatHistory;
	}
}
