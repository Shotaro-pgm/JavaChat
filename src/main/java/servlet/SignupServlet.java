package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import businesslogic.SignupLogic;
import javabean.UserBean;

public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// パラメータを変数に格納する
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String nickname = request.getParameter("nickname");
		
		// UserBeanをインスタンス化する
		UserBean userBean = new UserBean();
		
		// ビジネスロジックの実行メソッドを呼び出す
		SignupLogic sl = new SignupLogic();
		userBean = sl.execute(userBean, userName, password, firstName, lastName, nickname);
		
		// セッションスコープにuserBeanを保存する
		if(userBean.getUserName() != null) {
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("userBean", userBean);
		
		// フォワードする
		RequestDispatcher rd = request.getRequestDispatcher("sinuplogin.jsp");
		rd.forward(request, response);
		
	}

}
