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

import businesslogic.ChatroomListLogic;
import javabean.ChatroomBean;
import javabean.UserBean;

@WebServlet("/chatroomlist")
public class ChatroomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// セッションスコープからログインユーザの情報を取得する
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean)session.getAttribute("userBean");
		String userName = userBean.getUserName();
		
		// DBからの取得結果を格納するリストをインスタンス化する
		List<ChatroomBean> chatroomList = new ArrayList();
		
		// ビジネスロジックの実行メソッドを呼び出す
		ChatroomListLogic cll = new ChatroomListLogic();
		chatroomList = cll.execute(userName);
		
		// 取得結果をセッションスコープに保存する
		session.setAttribute("chatroomList", chatroomList);
		
		// リダイレクトする
		response.sendRedirect("home.jsp");
		
	}

}
