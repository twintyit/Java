package itstep.learning.db;

import com.google.inject.Inject;

import java.sql.*;

public class DbDemo {
    private final DatabaseService databaseService;
    @Inject
    public DbDemo(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void run(){

        System.out.println("Db Demo");

        try (Connection connection = databaseService.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet res = statement.executeQuery( "SHOW DATABASES" );

            while( res.next() ){
                System.out.println( res.getString( 1 ) );
            }
            res.close();
            statement.close();

        }
        catch(SQLException e) {
            System.out.println( e.getMessage() );
        }
    }
}
