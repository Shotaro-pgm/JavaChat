package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DbConnector {
	private static Connection rst;
	
	public Connection dbConnect() {
		Properties props = new Properties();
		HikariDataSource dataSource = null;
		
        try {
			props.load(new FileInputStream("/Users/itoshotaro/プログラミング/properties/JavaChat/hikari.properties"));
			
			HikariConfig config = new HikariConfig();
			config.setDriverClassName(props.getProperty("dataSourceClassName"));
	        config.setJdbcUrl(props.getProperty("dataSource.url"));
	        config.setUsername(props.getProperty("dataSource.user"));
	        config.setPassword(props.getProperty("dataSource.password"));
	        config.setMaximumPoolSize(Integer.parseInt(props.getProperty("maximumPoolSize")));
			dataSource = new HikariDataSource(config);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
        
		// コネクションプールからデータソースを取得
        try (Connection conn = dataSource.getConnection()) {
        	conn.setAutoCommit(false);
        	
        	rst = conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return rst;
	}
}
