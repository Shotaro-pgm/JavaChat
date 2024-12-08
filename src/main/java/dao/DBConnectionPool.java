package dao;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectionPool {

    private static HikariDataSource dataSource;

    static {
    	Properties props = new Properties();
    	String strpass = "/Users/itoshotaro/プログラミング/properties/JavaChat/hikari.properties";
    	
        try {
        	InputStream istream = new FileInputStream(strpass);
            props.load(istream);
            
            // HikariCP設定を反映
            HikariConfig config = new HikariConfig(props);
            dataSource = new HikariDataSource(config);
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }

    // DataSourceの取得
    public static DataSource getDataSource() {
        return dataSource;
    }
}