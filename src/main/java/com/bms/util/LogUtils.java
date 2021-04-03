package com.bms.util;

import com.bms.dao.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class LogUtils {
    public static Connection connection;

    static {
        try {
            connection = DatabaseConnect.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createLog(String text) throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("insert into log (text,time) values (?,?)");
        pstm.setString(1,text);
        pstm.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
        pstm.executeUpdate();
    }
}
