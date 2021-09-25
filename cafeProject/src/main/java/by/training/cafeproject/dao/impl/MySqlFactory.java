package by.training.cafeproject.dao.impl;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MySqlFactory {
    public static MysqlDataSource createMysqlDataSource() {
        MysqlDataSource dataSource = null;
        Properties props = new Properties();
        try {
            InputStream inputStream = new FileInputStream("db.properties");
            props.load(inputStream);
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("url"));
            dataSource.setUser(props.getProperty("user"));
            dataSource.setPassword(props.getProperty("password"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
