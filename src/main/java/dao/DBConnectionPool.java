package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnectionPool {

    private static HikariDataSource dataSource;

    static {
        try {
            // プロパティファイルの読み込み
            Properties props = new Properties();
            props.load(new FileInputStream("/Users/itoshotaro/プログラミング/properties/JavaChat/hikari.properties")); // プロパティファイルのパスを指定

            // HikariCP設定を反映
            /*
            HikariConfig config = new HikariConfig(props);
            dataSource = new HikariDataSource(config);
            */
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("dataSource.url"));
            config.setUsername(props.getProperty("dataSource.user"));
            config.setPassword(props.getProperty("dataSource.password"));
            config.setMaximumPoolSize(Integer.parseInt(props.getProperty("maximumPoolSize")));
            
            
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load HikariCP configuration", e);
        }
    }

    // DataSourceの取得
    public static DataSource getDataSource() {
        return dataSource;
    }
}