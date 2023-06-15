package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//since there are two databases to connect to in this task, there's an input string to which database to connect to
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
