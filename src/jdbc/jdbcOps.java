package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class jdbcOps {
    public Connection getConnection() {
        try{
            //TODO i stedet for library i url, legge til database jeg bruker i dag
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/library?useSSL=false",
                    "root",
                    "Passord"
            );
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
