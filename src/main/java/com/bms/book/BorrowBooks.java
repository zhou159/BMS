package com.bms.book;

import com.bms.book.BookBorrow;
import com.bms.book.BookCheck;
import com.bms.book.BookCheckReader;
import com.bms.book.BookReturn;
import com.bms.dao.DatabaseConnect;
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

public class BorrowBooks extends JFrame implements ActionListener {
    String name = null;

    Connection connection = DatabaseConnect.getConnection();

    JTable book=new JTable();

    private JButton bookReturn;
    private JButton back;
    private JButton refresh;
    private JLabel readerName;
    private JLabel table1;
    private JLabel table2;
    private JLabel table3;
    private JLabel table4;
    private JLabel table5;


    public BorrowBooks(String title,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(1000,700);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());

        this.name = name;


        init();
        this.setVisible(true);
    }
    public void init() throws SQLException {

        this.setLayout(null);//清空整个布局管理器
        PreparedStatement pstm = connection.prepareStatement("select * from br");
        ResultSet rs = pstm.executeQuery();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("书籍编号");
        columnNames.add("借书人姓名");
        columnNames.add("书籍名字");
        columnNames.add("借书时间");
        columnNames.add("剩余未归还量");

        Vector<Vector<String>> tableValues = new Vector<>();
        while (rs.next()){
            PreparedStatement pstm2 = connection.prepareStatement("select * from book where id=?");
            pstm2.setInt(1,rs.getInt(3));
            ResultSet resultSet = pstm2.executeQuery();

            PreparedStatement pstm3 = connection.prepareStatement("select * from reader where id=?");
            pstm3.setInt(1,rs.getInt(2));
            ResultSet resultSet1 = pstm3.executeQuery();

            while (resultSet.next() && resultSet1.next()){
                Vector<String> rowV = new Vector<>();

                rowV.add(resultSet.getString(1));
                rowV.add(resultSet1.getString(2));
                rowV.add(resultSet.getString(2)+"--"+resultSet.getString(3));
                rowV.add(rs.getString(4));
                rowV.add(rs.getString(6));
                tableValues.add(rowV);

                book = new JTable(tableValues,columnNames);
            }
        }

        book.setPreferredScrollableViewportSize(new Dimension(900, 550));
        JScrollPane s = new JScrollPane(book);
        getContentPane().add(s, BorderLayout.CENTER);
        book.revalidate();


        book.setBounds(35,65,900,550);
        add(book);

        bookReturn=new JButton("还书");
        bookReturn.setBounds(250,15,90,25);
        bookReturn.setBackground(Color.yellow);
        add(bookReturn);

        readerName=new JLabel(name+",你的借阅记录");
        readerName.setBounds(50,15,150,25);
        readerName.setForeground(Color.red);
        add(readerName);

        table1=new JLabel("书籍编号");
        table1.setBounds(120,45,100,25);
        table1.setForeground(Color.blue);
        add(table1);

        table5=new JLabel("借书人姓名");
        table5.setBounds(260,45,100,25);
        table5.setForeground(Color.blue);
        add(table5);

        table2=new JLabel("书籍名字");
        table2.setBounds(440,45,100,25);
        table2.setForeground(Color.blue);
        add(table2);

        table3=new JLabel("借书时间");
        table3.setBounds(630,45,100,25);
        table3.setForeground(Color.blue);
        add(table3);

        table4=new JLabel("剩余未归还量");
        table4.setBounds(810,45,100,25);
        table4.setForeground(Color.blue);
        add(table4);


        back=new JButton("返回");
        back.setBounds(650,15,90,25);
        back.setBackground(Color.blue);
        add(back);

        refresh=new JButton("刷新");
        refresh.setBounds(800,15,70,25);
        add(refresh);

        bookReturn.addActionListener(this);
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
            new BorrowBooks("图书借阅情况",name);
            this.dispose();
        }
    }
}
