package eTicaret.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/eticaret";
    private static final String JDBC_USER = "eadmin";
    private static final String JDBC_PASSWORD = "1234";

    static {
        try {
            // MySQL JDBC sürücüsünü yükleyin
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC sürücüsü bulunamadı.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

