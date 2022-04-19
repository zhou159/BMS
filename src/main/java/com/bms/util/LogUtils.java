package com.bms.util;

import com.bms.dao.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class LogUtils{
    static Connection connection = DatabaseConnect.getConnection();

    public static void createLog(String text) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("insert into log (text,time) values (?,?)");
        pstm.setString(1,text);
        pstm.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
        pstm.executeUpdate();
    }
}
