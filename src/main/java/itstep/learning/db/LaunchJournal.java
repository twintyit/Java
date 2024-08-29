package itstep.learning.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.inject.Inject;

public class LaunchJournal {
    private final Connection connection;

    @Inject
    public LaunchJournal(Connection connection) {
        this.connection = connection;
    }

    public void logLaunch() {
        String insertSQL = "INSERT INTO launch_journal (launch_time) VALUES (CURRENT_TIMESTAMP)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertSQL);
            System.out.println("Запуск записан в журнал.");
        } catch (SQLException e) {
            System.out.println("Ошибка при записи запуска: " + e.getMessage());
        }
    }

    public void printJournal() {
        String querySQL = "SELECT * FROM launch_journal";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(querySQL);

            System.out.println("Журнал запусков:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Время запуска: " + resultSet.getTimestamp("launch_time"));
            }
        } catch (SQLException e) {
            System.out.println("Ошибка при чтении журнала: " + e.getMessage());
        }
    }
}
