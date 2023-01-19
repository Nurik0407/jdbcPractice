package peaksoft.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getConnection() throws SQLException {
        try {
            System.out.println("Successfully connected");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/java8", "postgres", "postgres");

        } catch (SQLException e) {
            throw new SQLException();

        }
    }
}
