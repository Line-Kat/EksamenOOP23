package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCOps {
    public Connection getConnection(String database) {
        try{
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + database + "?useSSL=false",
                    "root",
                    "Passord"
            );
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
