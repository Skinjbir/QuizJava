package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DBConnector class provides a method to establish a connection to the database.
 * It uses JDBC to connect to a MySQL database.
 */
public class DBConnector {
    // Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/quiz_db";
    // Database username
    private static final String DB_USER = "root";
    // Database password
    private static final String DB_PASSWORD = "root";

    /**
     * Establishes and returns a connection to the database.
     *
     * @return Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}