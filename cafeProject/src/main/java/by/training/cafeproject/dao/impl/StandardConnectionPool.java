package by.training.cafeproject.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class StandardConnectionPool {
    private static final Logger standardConnectionPoolLogger = LogManager.getLogger(StandardConnectionPool.class);
    private static final String DATASOURCE_NAME = "jdbc/cafe_db";
    private static DataSource dataSource;
    static {
//        try {
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = MySqlFactory.createMysqlDataSource();
            standardConnectionPoolLogger.info("mysql data source created");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
    }
    private StandardConnectionPool() {
    }
    public static Connection getConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }
}
