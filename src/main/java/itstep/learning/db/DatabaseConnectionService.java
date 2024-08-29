package itstep.learning.db;

import com.google.inject.Provider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService implements DatabaseService {

    private static final String URL = "jdbc:mysql://localhost:3308/java_pv222?useUnicode=true&characterEncoding=utf8";
    private static final String USER = "twintyit";
    private static final String PASSWORD = "qwerty";

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
