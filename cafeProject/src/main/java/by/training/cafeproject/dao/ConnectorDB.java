package by.training.cafeproject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectorDB {
    public static Connection getConnection() {
        ResourceBundle resource = ResourceBundle.getBundle("db");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.getMessage();
            return null;
        }
    }
}
