package com.bms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    private static String url = null;
    private static String username= null;
    private static String password = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            url="jdbc:mysql://localhost:3306/bms.db?serverTimezone=UTC&useSSL=true&useUnicode=true&characterEncoding=UTF-8";
            username="root";
            password="123456";

        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

}
