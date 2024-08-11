package businesslogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import dao.LoginDAO;
import javabean.UserBean;

public class LoginLogic {
	public UserBean execute(UserBean userBean, String userName, String password) {
		try {
			// パスワードを暗号化する
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256Byte = sha256.digest(password.getBytes());
			
			HexFormat hex = HexFormat.of().withLowerCase();
			password = hex.formatHex(sha256Byte);
			
			// userBeanにユーザ名と暗号化したパスワードを設定する
			userBean.setUserName(userName);
			userBean.setPassword(password);
			
			// DAOクラスの実行メソッドを呼び出す
			LoginDAO ld = new LoginDAO();
			userBean = ld.execute(userBean);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return userBean;
	}

}
