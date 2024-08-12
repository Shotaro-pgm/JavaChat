package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import businesslogic.LoginLogic;
import javabean.UserBean;

@WebServlet("login")
public class LoginServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// 送信されたログイン情報を変数に格納する
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		// UserBeanをインスタンス化する
		UserBean userBean = new UserBean();
		
		// LoginLogicの実行メソッドをよびだす
		LoginLogic ll = new LoginLogic();
		userBean = ll.execute(userBean, userName, password);
		
		// userBeanをセッションスコープに保存する
		HttpSession session = request.getSession();
		session.setAttribute("userBean", userBean);
		
		// フォワードする
		RequestDispatcher rd = request.getRequestDispatcher("chatroomlist");
		rd.forward(request, response);
	}

}
