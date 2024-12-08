package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javabean.ChatroomBean;

public class ChatroomListDAO extends DbConnector {
	private Connection conn = null;
	
	public List<ChatroomBean> execute(String userName){
		List<ChatroomBean> list = new ArrayList();
		
		try {
			conn = dbConnect();
			
			String sql = "select distinct recipient from message where sender = \"" + userName + "\" order by sendTime desc;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = null;
			rs = ps.executeQuery();
			
			ChatroomBean chatroomBean = new ChatroomBean();
			while(rs.next()) {
				chatroomBean.setRecipient(rs.getString("recipient"));
				list.add(chatroomBean);
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
		
		return list;
	}
}
