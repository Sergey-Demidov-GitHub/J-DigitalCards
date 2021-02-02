/*  Copyright (C) 2021 Sergey Demidov   */

package dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final String SQCONN = "jdbc:sqlite:Cards.sqlite";

    public static Connection getConnection() throws SQLException {
        try {
            Properties properties = new Properties();
            properties.setProperty("foreign_keys", "true");
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(SQCONN, properties);
        } catch (ClassNotFoundException exception){
            exception.printStackTrace();
        }
        return null;
    }
}
