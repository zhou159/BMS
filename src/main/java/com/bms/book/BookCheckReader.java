package com.bms.book;

import com.bms.dao.DatabaseConnect;
import com.bms.reader.ReaderBorrowBooks;
import com.bms.reader.Readerjm;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;


public class BookCheckReader extends JFrame implements ActionListener {

    int readId = 0;
    String name = null;

    Connection connection = DatabaseConnect.getConnection();

    JTable book=new JTable();

    private JButton bookReturn;
    private JButton bookBorrow;
    private JButton readerBookBorrow;
    private JLabel readerName;
    private JButton back;
    private JButton refresh;

    private JLabel table1;
    private JLabel table2;
    private JLabel table3;
    private JLabel table4;
    private JLabel table5;
    private JLabel table6;

    public BookCheckReader(String title,int readId,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(950,700);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.readId = readId;
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

        book.setBounds(15,75,900,525);
        add(book);

        readerName=new JLabel(name+",图书信息如下!");
        readerName.setBounds(50,15,150,25);
        readerName.setForeground(Color.red);
        add(readerName);

        table1=new JLabel("图书编号");
        table1.setBounds(70,50,90,25);
        table1.setForeground(Color.blue);
        add(table1);

        table2=new JLabel("图书名字");
        table2.setBounds(220,50,90,25);
        table2.setForeground(Color.blue);
        add(table2);

        table3=new JLabel("图书作者");
        table3.setBounds(370,50,90,25);
        table3.setForeground(Color.blue);
        add(table3);

        table4=new JLabel("图书价格");
        table4.setBounds(515,50,90,25);
        table4.setForeground(Color.blue);
        add(table4);

        table5=new JLabel("图书库存");
        table5.setBounds(670,50,90,25);
        table5.setForeground(Color.blue);
        add(table5);

        table6=new JLabel("图书出版社");
        table6.setBounds(800,50,90,25);
        table6.setForeground(Color.blue);
        add(table6);

        bookReturn=new JButton("还书");
        bookReturn.setBounds(200,15,90,25);
        bookReturn.setBackground(Color.yellow);
        add(bookReturn);

        bookBorrow=new JButton("借书");
        bookBorrow.setBounds(350,15,90,25);
        bookBorrow.setBackground(Color.yellow);
        add(bookBorrow);

        readerBookBorrow=new JButton("已借阅的书籍");
        readerBookBorrow.setBounds(555,15,150,25);
        add(readerBookBorrow);

        back=new JButton("返回");
        back.setBounds(800,615,90,25);
        back.setBackground(Color.blue);
        add(back);

        refresh=new JButton("刷新");
        refresh.setBounds(800,15,70,25);
        add(refresh);

        bookReturn.addActionListener(this);
        bookBorrow.addActionListener(this);
        readerBookBorrow.addActionListener(this);
        back.addActionListener(this);
        refresh.addActionListener(this);
    }
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {


        if (e.getSource()==bookReturn) {
            new BookReturn("还书",readId,name);
            this.dispose();
        }

        if (e.getSource()==bookBorrow) {
            new BookBorrow("借书",readId,name);
            this.dispose();
        }

        if (e.getSource()==readerBookBorrow){
            new ReaderBorrowBooks("已借阅书籍",name,readId);
            this.dispose();
        }

        if (e.getSource()==back) {
            new Readerjm("读者界面",name,readId);
            this.dispose();
        }

        if(e.getSource()==refresh){
            new BookCheckReader("图书信息",readId,name);
            this.dispose();
        }
    }
}
