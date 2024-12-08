package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ChatroomBean;

public class GetChatHistoryDAO extends DbConnector {
		private Connection conn = null;
		
		public List<ChatroomBean> execute(String sender, String recipient){
			List<ChatroomBean> chatHistory = new ArrayList();
			
			try {
				conn = dbConnect();
				
				String sql = "select content, sender from message where (sender = "
						+ "\"" + sender + "\" and recipient = \"" + recipient 
						+ "\") or (sender = \"" + recipient + "\" and recipient = \"" 
						+ sender + "\") order by sendTime desc;";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = null;
				rs = ps.executeQuery();
				
				ChatroomBean chatroomBean = null;
				while(rs.next()) {
					String content = rs.getString("content");
					sender = rs.getString("sender");
					
					chatroomBean = new ChatroomBean(sender, content);
					
					chatHistory.add(chatroomBean);
				}
				
				conn.commit();
			} catch(SQLException e) {
				if(conn != null) {
					try {
						conn.rollback();
					} catch(SQLException e2) {
						e2.printStackTrace();
					}
				}
			}
			
			return chatHistory;
		}

}
