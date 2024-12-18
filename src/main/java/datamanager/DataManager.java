package datamanager;

import io.github.cdimascio.dotenv.Dotenv;

public class DataManager {
	private Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
	
	private String URL = dotenv.get("DATABASE_URL", System.getenv("DATABASE_URL"));
	private String USER = dotenv.get("POSTGRE_USER", System.getenv("POSTGRE_USER"));
	private String PASSWORD = dotenv.get("POSTGRE_PASSWORD", System.getenv("POSTGRE_PASSWORD"));;
	private String JDBC_DRIVER = "org.postgresql.Driver";
	
	private String LOGIN_SERVLET = "LoginServlet";

	private String MSG_RECEIVED_REQUEST = "Received request";
	private String MSG_NULL_USERNAME = "null userName";
	private String MSG_NULL_PASSWORD = "null password";
	
	private String accountUserName = "userName";
	private String accountPassword = "password";
	
	private String chatroomList = "chatroomlist";
	
	public String getAccountUserName() {
		return accountUserName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public String getChatroomList() {
		return chatroomList;
	}
	public Dotenv getDotenv() {
		return dotenv;
	}
	public String getURL() {
		return URL;
	}
	public String getUSER() {
		return USER;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}
	public String getLOGIN_SERVLET() {
		return LOGIN_SERVLET;
	}
	public String getMSG_RECEIVED_REQUEST() {
		return MSG_RECEIVED_REQUEST;
	}
	public String getMSG_NULL_USERNAME() {
		return MSG_NULL_USERNAME;
	}
	public String getMSG_NULL_PASSWORD() {
		return MSG_NULL_PASSWORD;
	}
	
	

}
