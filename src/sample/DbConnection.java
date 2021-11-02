package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static DbConnection getInstance(){
        return new DbConnection();
    }

    public Connection getConnection() throws SQLException {
        String jdbcUrl = "jdbc:sqlite:/C:\\sqlite\\sqlite-tools-win32-x86-3360000\\test.db";
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(jdbcUrl);
        }catch(SQLException e){
            System.out.println("Error Connecting to Database");
        }

        return connection;

    }
}
