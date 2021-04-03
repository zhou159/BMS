package com.bms.book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.bms.admin.Adminjm;
import com.bms.admin.Logjm;
import com.bms.dao.DatabaseConnect;
import com.bms.util.LogUtils;
import lombok.SneakyThrows;


public class BookCheck extends JFrame implements ActionListener {

    Connection connection = DatabaseConnect.getConnection();

    JTable book = new JTable(100,6);

    String name = null;


    private JButton bookadd;
    private JButton bookModification;
    private JButton bookDelete;
    private JButton back;
    private JButton bookBr;
    private JButton refresh;
    private JButton log;


    public BookCheck(String title,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(950,700);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.name = name;

        init();

        this.setVisible(true);


    }
    public void init() throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("select * from book");
        ResultSet rs = pstm.executeQuery();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("编号");
        columnNames.add("书名");
        columnNames.add("作者");
        columnNames.add("价格");
        columnNames.add("库存量");
        columnNames.add("出版社");

        Vector<Vector<String>> tableValues = new Vector<>();
        while (rs.next()){
                Vector<String> rowV = new Vector<>();
                rowV.add(rs.getString(1));
                rowV.add(rs.getString(2));
                rowV.add(rs.getString(3));
                rowV.add(rs.getString(4));
                rowV.add(rs.getString(5));
                rowV.add(rs.getString(6));
                tableValues.add(rowV);

            book = new JTable(tableValues,columnNames);
        }


        book.setPreferredScrollableViewportSize(new Dimension(900, 550));
        JScrollPane s = new JScrollPane(book);
        getContentPane().add(s, BorderLayout.CENTER);
        book.revalidate();
        this.setLayout(null);//清空整个布局管理器

        book.setBounds(15,55,900,550);
        add(book);

        bookadd=new JButton("新增图书");
        bookadd.setBounds(100,15,90,25);
        add(bookadd);

        bookModification=new JButton("修改信息");
        bookModification.setBounds(250,15,90,25);
        add(bookModification);

        bookDelete=new JButton("删除信息");
        bookDelete.setBounds(400,15,90,25);
        bookDelete.setBackground(Color.red);
        add(bookDelete);

        back=new JButton("返回");
        back.setBounds(800,615,90,25);
        back.setBackground(Color.blue);
        add(back);

        bookBr=new JButton("书籍借阅情况");
        bookBr.setBounds(550,15,120,25);
        add(bookBr);

        refresh=new JButton("刷新");
        refresh.setBounds(750,15,70,25);
        add(refresh);

        log=new JButton("日志");
        log.setBounds(820,15,70,25);
        add(log);


        bookadd.addActionListener(this);
        bookModification.addActionListener(this);
        bookDelete.addActionListener(this);
        back.addActionListener(this);
        refresh.addActionListener(this);
        log.addActionListener(this);
        bookBr.addActionListener(this);
    }


    @SneakyThrows
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==bookadd){
            new BookAdd("新增图书信息",name);
            this.dispose();
        }

        if (e.getSource()==bookModification) {
            new BookModification("修改图书信息",name);
            this.dispose();
        }

        if (e.getSource()==bookDelete) {
            // TODO Auto-generated method stub
            // 删除指定行
            int count = book.getSelectedRow();
            String id = book.getValueAt(count,0).toString();

            PreparedStatement pstm2 = connection.prepareStatement("select * from book where id=?");
            pstm2.setInt(1,Integer.valueOf(id).intValue());
            ResultSet resultSet = pstm2.executeQuery();

            PreparedStatement pstm = connection.prepareStatement("delete from book where id=?");
            pstm.setInt(1,Integer.valueOf(id).intValue());
            pstm.executeUpdate();
            if(resultSet.next()){
                LogUtils.createLog(name+",删除了图书:"+resultSet.getString(2));
                new BookCheck("图书维护",name);
                this.dispose();
            }
        }

        if (e.getSource()==bookBr){
            new BorrowBooks("图书借阅情况",name);
            this.dispose();
        }

        if (e.getSource()==back) {
            new Adminjm("图书维护",name);
            this.dispose();
        }

        if(e.getSource()==refresh){
            new BookCheck("图书信息",name);
            this.dispose();
        }

        if(e.getSource()==log){
            new Logjm("日志信息",name);
            this.dispose();
        }
    }
}
