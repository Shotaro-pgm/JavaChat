package businesslogic;

import java.util.ArrayList;
import java.util.List;

import dao.ChatroomListDAO;
import javabean.ChatroomBean;

public class ChatroomListLogic {
	public List<ChatroomBean> execute(String userName){
		// 取得結果を返却するリストをインスタンス化する
		List<ChatroomBean> list = new ArrayList();
		
		// DAOクラスの実行メソッドを呼び出す
		ChatroomListDAO cld = new ChatroomListDAO();
		list = cld.execute(userName);
		
		return list;
	}

}
