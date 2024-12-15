package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import businesslogic.LoginLogic;
import javabean.UserBean;
import util.LogExporter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserBean userBean = null;
	private LogExporter logExporter = new LogExporter();
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		logExporter.runLog();
		
		// 送信されたログイン情報を変数に格納する
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		// UserBeanをインスタンス化する
		this.userBean = new UserBean();
		
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
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		logExporter.runLog();
		
		// ログイン情報を破棄する
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("index.jsp");
	}

}
