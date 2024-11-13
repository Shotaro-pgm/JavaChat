package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import businesslogic.SendMessageLogic;
import businesslogic.ViewChatroomLogic;
import javabean.ChatroomBean;
import javabean.UserBean;

@WebServlet("/send")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// パラメータを変数に格納する
		// 送信者
		HttpSession session = request.getSession();
		UserBean loginUser = (UserBean)session.getAttribute("userBean");
		String sender = loginUser.getUserName();
		// 受信者
		String recipient = (String)session.getAttribute("recipient");
		// メッセージ
		String content = request.getParameter("content");
		
		// 確認用
		System.out.println(sender);
		System.out.println(recipient);
		System.out.println(content);
		
		// メソッドの実行結果を保存する変数を宣言する
		int result = 0;
		
		// メッセージ送信のロジッククラスの実行メソッドを呼び出す
		SendMessageLogic sml = new SendMessageLogic();
		result = sml.execute(sender, recipient, content);
		
		// 確認用
		System.out.println(result);
		
		// 取得結果を判定する
		if(result == 0) {
			// 確認用
			System.out.println("確認用：メッセージ送信に失敗");
			
			String errorMsg = "メッセージの送信に失敗しました。";
			session.setAttribute("error_msg", errorMsg);
		}
		
		// チャットルーム表示のロジッククラスをインスタンス化する
		ViewChatroomLogic vcl = new ViewChatroomLogic();
		
		// 取得結果返却用のリストをインスタンス化する
		List<ChatroomBean> chatHistory = new ArrayList();
		chatHistory = vcl.execGetChatHistory(sender, recipient);
		
		// 取得結果をセッションスコープに保存する
		session.setAttribute("chathistory", chatHistory);
		
		// リダイレクト
		response.sendRedirect("chatroom.jsp");
		
	}

}
