package itstep.learning.db;

import com.google.inject.Provider;
import itstep.learning.fs.ConfigReader;
import itstep.learning.fs.ConfigWriter;
import itstep.learning.fs.DbModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService implements DatabaseService {

    private final ConfigReader cw;

    public DatabaseConnectionService(){
        cw = new ConfigReader();
    }

    @Override
    public Connection getConnection() {
        try {
            DbModel dbModel = cw.readConfigAndCreateConnectionString();
            return DriverManager.getConnection( dbModel.getURL(), dbModel.getUSER(), dbModel.getPASSWORD() );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to the database", e);
        }
    }
}
