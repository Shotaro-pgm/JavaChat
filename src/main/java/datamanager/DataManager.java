package datamanager;

import io.github.cdimascio.dotenv.Dotenv;

public class DataManager {
	private Dotenv dotenv = Dotenv.load();
	
	private String DATABASE_NAME = dotenv.get("DATABASE_NAME");
	private String URL = dotenv.get("DATABASE_URL") + ":" + dotenv.get("RDB_NAME") + ":" + dotenv.get("DATABASE_HOST") + ":" + dotenv.get("DATABASE_PORT") +  DATABASE_NAME;
	private String USER = dotenv.get("POSTGRE_USER");
	private String PASSWORD = dotenv.get("POSTGRE_PASSWORD");;
	private String JDBC_DRIVER = "org.postgresql.Driver";
	
	private String LOGIN_SERVLET = "LoginServlet";
	
	public String getLOGIN_SERVLET() {
		return LOGIN_SERVLET;
	}

	public void setLOGIN_SERVLET(String lOGIN_SERVLET) {
		LOGIN_SERVLET = lOGIN_SERVLET;
	}

	private String MSG_RECEIVED_REQUEST = "Received request";
	private String MSG_NULL_USERNAME = "null userName";
	private String MSG_NULL_PASSWORD = "null password";
	
	
	public String getMSG_NULL_PASSWORD() {
		return MSG_NULL_PASSWORD;
	}

	public void setMSG_NULL_PASSWORD(String mSG_NULL_PASSWORD) {
		MSG_NULL_PASSWORD = mSG_NULL_PASSWORD;
	}

	public String getMSG_NULL_USERNAME() {
		return MSG_NULL_USERNAME;
	}

	public void setMSG_NULL_USERNAME(String mSG_NULL_USERNAME) {
		MSG_NULL_USERNAME = mSG_NULL_USERNAME;
	}

	private String accountUserName = "userName";
	private String accountPassword = "password";
	
	private String chatroomList = "chatroomlist";

	public String getDATABASE_NAME() {
		return DATABASE_NAME;
	}

	public void setDATABASE_NAME(String dATABASE_NAME) {
		DATABASE_NAME = dATABASE_NAME;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getUSER() {
		return USER;
	}

	public void setUSER(String uSER) {
		USER = uSER;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}

	public void setJDBC_DRIVER(String jDBC_DRIVER) {
		JDBC_DRIVER = jDBC_DRIVER;
	}

	public String getMSG_RECEIVED_REQUEST() {
		return MSG_RECEIVED_REQUEST;
	}

	public void setMSG_RECEIVED_REQUEST(String mSG_RECEIVED_REQUEST) {
		MSG_RECEIVED_REQUEST = mSG_RECEIVED_REQUEST;
	}

	public String getAccountUserName() {
		return accountUserName;
	}

	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getChatroomList() {
		return chatroomList;
	}

	public void setChatroomList(String chatroomList) {
		this.chatroomList = chatroomList;
	}
	

}
