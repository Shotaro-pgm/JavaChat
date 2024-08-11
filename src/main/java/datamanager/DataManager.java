package datamanager;

public class DataManager {
	private String DATABASE_NAME = "JavaChat";
	private String PROPATIES = "?characterEncoding=UTF-8&useSSL=false";
	private String URL = "jdbc:mySQL://localhost/" + DATABASE_NAME + PROPATIES;
	private String USER = "root";
	private String PASSWORD = "password";
	private String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	public String getDATABASE_NAME() {
		return DATABASE_NAME;
	}
	public String getPROPATIES() {
		return PROPATIES;
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

}
