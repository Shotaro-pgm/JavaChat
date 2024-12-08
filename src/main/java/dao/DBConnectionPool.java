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
            props.load(new FileInputStream("src/main/resources/hikari.properties")); // プロパティファイルのパスを指定

            // HikariCP設定を反映
            HikariConfig config = new HikariConfig(props);
            dataSource = new HikariDataSource(config);
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