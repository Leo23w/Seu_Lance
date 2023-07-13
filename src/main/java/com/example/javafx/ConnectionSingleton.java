package com.example.javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    public static Connection connection;

    public static Connection getConnection() throws SQLException {


        if (connection == null) {
            connection = DriverManager.getConnection(//
                    "jdbc:mysql://localhost:3306/mydb", //
                    "root", //
                    "");
        }
        return connection;
    }
}