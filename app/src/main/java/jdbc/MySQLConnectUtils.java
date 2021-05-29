package main.java.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectUtils {
    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";
        String dbname = "user";
        String userName = "nkcong206";
        String password = "cong2006";
        return getMySQLConnection(hostName, dbname, userName, password);
    }

    private static Connection getMySQLConnection(String hostName, String dbname, String userName, String password) throws SQLException, ClassNotFoundException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbname;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
