package com.bms.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnect {
	private static String driver = null;
    private static String url = null;
    private static String username= null;
    private static String password = null;

    public static void init(){
	   Properties p = new Properties();
	   try {
		   InputStream in = DatabaseConnect.class.getClassLoader().getResourceAsStream("sqlConnect.properties");
		   p.load(in);
		   
		   driver = (String) p.get("ClassDriver");
		   url = (String) p.get("url");
		   username = (String) p.get("user");
		   password = (String) p.get("password");
	   } catch (FileNotFoundException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
	   }
    }
    
    public static Connection getConnection(){
    	init();
    	try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }

}
