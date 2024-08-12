package businesslogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

import dao.SignupDAO;
import javabean.UserBean;

public class SignupLogic {
	public UserBean execute(UserBean userBean, String userName, String password, String firstName,
			String lastName, String nickname) {
		if(userName == null || password == null || firstName == null ||  lastName == null) {
			return userBean;
		}
		
		try {
			// パスワードを暗号化する
			// passwordを暗号化処理用の変数に格納する
			String encPassword = password;
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] sha256Byte = sha256.digest(encPassword.getBytes());
			
			HexFormat hex = HexFormat.of().withLowerCase();
			encPassword = hex.formatHex(sha256Byte);
			
			// userBeanに登録情報を設定する
			userBean.setUserName(userName);
			userBean.setPassword(encPassword);
			userBean.setFirstName(firstName);
			userBean.setLastLoginDate(lastName);
			userBean.setNickname(nickname);
			
			// DAOクラスの実行メソッドを呼び出す
			SignupDAO sd = new SignupDAO();
			sd.execute(userBean);
			
			// UserBeanのインスタンスを破棄する
			userBean = null;
			
			// 返却用のインスタンスを生成する
			userBean = new UserBean();
			userBean.setUserName(userName);
			userBean.setPassword(password);
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return userBean;
	}

}
