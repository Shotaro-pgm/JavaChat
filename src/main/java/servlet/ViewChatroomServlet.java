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

import businesslogic.ViewChatroomLogic;
import javabean.ChatroomBean;
import javabean.UserBean;

@WebServlet("/viewchat")
public class ViewChatroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// 送信者と受信者のデータを格納する
		// 送信者
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		String sender = userBean.getUserName();
		// 受信者
		String recipient = request.getParameter("recipient");
		session.setAttribute("recipient", recipient);
		
		// 新規送信先のユーザが存在するかを確認する
		ViewChatroomLogic vcl = new ViewChatroomLogic();
		boolean result = false;
		result = vcl.execUserSearch(recipient);
		
		// 取得結果を判定する
		if(!result) {
			// エラーメッセージをセッションスコープに保存する
			String errorMsg = "指定したユーザは存在しません。";
			session.setAttribute("error_msg", errorMsg);
			
			// リダイレクト
			response.sendRedirect("home.jsp");
		}
		
		// 空のリストをセッションスコープに保存する
		List<ChatroomBean> chatHistory = new ArrayList();
		session.setAttribute("chathistory", chatHistory);
		
		// リダイレクト
		response.sendRedirect("chatroom.jsp");
		
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		//　送信者と受信者のデータを格納する
		// 送信者
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		String sender = userBean.getUserName();
		// 受信者
		String recipient = request.getParameter("recipient");
		session.setAttribute("recipient", recipient);
		
		// チャット履歴取得のロジッククラスをインスタンス化する
		ViewChatroomLogic vcl = new ViewChatroomLogic();
		List<ChatroomBean> chatHistory = new ArrayList();
		chatHistory = vcl.execGetChatHistory(sender, recipient);
		
		// 取得結果をセッションスコープに保存する
		session.setAttribute("chathistory", chatHistory);
		
		// リダイレクト
		response.sendRedirect("chatroom.jsp");
	}

}
