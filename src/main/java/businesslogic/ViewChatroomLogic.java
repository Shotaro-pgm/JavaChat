package businesslogic;

import java.util.ArrayList;
import java.util.List;

import dao.GetChatHistoryDAO;
import dao.SearchUserDAO;
import javabean.ChatroomBean;

public class ViewChatroomLogic {
	public boolean execUserSearch(String recipient) {
		SearchUserDAO sud = new SearchUserDAO();
		String rcpCheck = sud.execute(recipient);
		
		// 取得結果を判定する
		if(rcpCheck == recipient) {
			return true;
		}
		
		return false;
	}
	
	public List<ChatroomBean> execGetChatHistory(String sender, String recipient){
		// 返却用のリストをインスタンス化する
		List<ChatroomBean> chatHistory = new ArrayList();
		
		GetChatHistoryDAO gchd = new GetChatHistoryDAO();
		chatHistory = gchd.execute(sender, recipient);
		
		return chatHistory;
	}

}
