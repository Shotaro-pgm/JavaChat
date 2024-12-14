package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ChatroomBean;

public class ChatroomListDAO extends DBConnctor {
	public List<ChatroomBean> execute(String userName){
		Connection conn = null;
		List<ChatroomBean> list = new ArrayList();
		
		try {
			conn = DbConnect();
			
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
			
		} catch(SQLException e) {
			if(conn != null) {
				try {
					conn.rollback();
					e.printStackTrace();
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
