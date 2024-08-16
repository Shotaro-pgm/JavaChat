package businesslogic;

import java.util.ArrayList;
import java.util.List;

import dao.GetChatHistoryDAO;
import dao.SearchUserDAO;
import javabean.ChatroomBean;

public class ViewChatroomLogic {
	public boolean execUserSearch(String recipient) {
		// 返却用の変数を宣言する
		boolean result = false;
		
		SearchUserDAO sud = new SearchUserDAO();
		String rcpCheck = sud.execute(recipient);
		
		// 確認用
		System.out.println(rcpCheck);
		
		// 取得結果を判定する
		if(rcpCheck.equals(recipient)) {
			// 確認用
			System.out.println("条件分岐問題なし");
			result = true;
		}
		
		// 確認用
		System.out.println(result);
		
		return result;
	}
	
	public List<ChatroomBean> execGetChatHistory(String sender, String recipient){
		// 返却用のリストをインスタンス化する
		List<ChatroomBean> chatHistory = new ArrayList();
		
		GetChatHistoryDAO gchd = new GetChatHistoryDAO();
		chatHistory = gchd.execute(sender, recipient);
		
		for(ChatroomBean c : chatHistory) {
			System.out.println("ロジッククラスで確認：" + c.getContent());
		}
		
		return chatHistory;
	}

}
