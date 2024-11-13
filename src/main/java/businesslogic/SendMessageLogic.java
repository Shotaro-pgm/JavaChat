package businesslogic;

import dao.SendMessageDAO;

public class SendMessageLogic {
	public int execute(String sender, String recipient, String message) {
		// 結果取得用の変数を宣言する
		int result = 0;
		
		SendMessageDAO smd = new SendMessageDAO();
		result = smd.execute(sender, recipient, message);
		
		return result;
	}

}
