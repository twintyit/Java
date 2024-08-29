package itstep.learning.db;

import java.sql.Connection;

public interface DatabaseService {
    Connection getConnection();
}
