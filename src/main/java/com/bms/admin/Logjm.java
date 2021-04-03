package com.bms.admin;

import com.bms.book.BookAdd;
import com.bms.book.BookCheck;
import com.bms.book.BookModification;
import com.bms.dao.DatabaseConnect;
import com.bms.util.LogUtils;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Logjm extends JFrame implements ActionListener {
    Connection connection = DatabaseConnect.getConnection();

    JTable log = new JTable(100,2);

    String name = null;

    private JButton back;
    private JButton refresh;


    public Logjm(String title,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(1980,1080);
        this.setResizable(true);

        this.setLocationRelativeTo(getOwner());
        this.name = name;

        init();

        this.setVisible(true);


    }

    public void init() throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("select * from log");
        ResultSet rs = pstm.executeQuery();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("日志信息");
        columnNames.add("时间");

        Vector<Vector<String>> tableValues = new Vector<>();
        while (rs.next()){
            Vector<String> rowV = new Vector<>();
            rowV.add(rs.getString(2));
            rowV.add(rs.getString(3));
            tableValues.add(rowV);

            log = new JTable(tableValues,columnNames);
        }


        log.setPreferredScrollableViewportSize(new Dimension(900, 550));
        JScrollPane s = new JScrollPane(log);
        getContentPane().add(s, BorderLayout.CENTER);
        log.revalidate();
        this.setLayout(null);//清空整个布局管理器

        log.setBounds(15,55,1880,930);
        add(log);



        back=new JButton("返回");
        back.setBounds(795,15,90,25);
        back.setBackground(Color.blue);
        add(back);

        refresh=new JButton("刷新");
        refresh.setBounds(1020,15,70,25);
        add(refresh);

        back.addActionListener(this);
        refresh.addActionListener(this);
    }


    @SneakyThrows
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==back) {
            new BookCheck("图书信息",name);
            this.dispose();
        }

        if(e.getSource()==refresh){
            new Logjm("日志信息",name);
            this.dispose();
        }
    }
}
