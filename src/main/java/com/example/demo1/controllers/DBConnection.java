package com.example.demo1.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/dbdemo";
    private static final String USER = "root";
    private static final String PASSWORD = "hoangtien123";

    public static Connection getConnection() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
