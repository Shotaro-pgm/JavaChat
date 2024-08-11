package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// 送信されたログイン情報を変数に格納する
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		// UserBeanをインスタンス化する
	}

}
