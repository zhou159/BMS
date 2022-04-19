package com.bms.reader;

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

public class ReaderBorrowBooks extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int readId = 0;
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

    public ReaderBorrowBooks(String title,String name,int readId) throws SQLException {
        this.setTitle(title);
        this.setSize(1000,700);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.readId = readId;
        this.name = name;

        init();
        this.setVisible(true);
    }
    public void init() throws SQLException {

        this.setLayout(null);//清空整个布局管理器
        PreparedStatement pstm = connection.prepareStatement("select * from br where reader_id=? and amount != 0");
        pstm.setInt(1,readId);
        ResultSet rs = pstm.executeQuery();
        
        rs.last();/*将游标移动到最后一行记录，用于统计总行数*/

        Vector<Vector<String>> tableValues = new Vector<>();/*表格行*/
        
        if(rs.getRow() == 0) {//总行数为0时
        	Vector<String> columnNames = new Vector<>();
            columnNames.add("书籍编号");/*表格列，有几列就add几次*/
            
        	Vector<String> rowV = new Vector<>();
        	rowV.add("您暂时还没有未归还的书籍！");
        	
        	tableValues.add(rowV);
        	book = new JTable(tableValues,columnNames);
        }else {//总行数不为0时
        	rs.beforeFirst();/*将游标移动回第一行记录，开始循环*/
        	while (rs.next()) {
                PreparedStatement pstm2 = connection.prepareStatement("select * from book where id=?");
                pstm2.setInt(1, rs.getInt(3));
                ResultSet resultSet = pstm2.executeQuery();
                while (resultSet.next()) {
                    Vector<String> columnNames = new Vector<>();
                    columnNames.add("书籍编号");
                    columnNames.add("书籍名字");
                    columnNames.add("借书时间");
                    columnNames.add("剩余未归还量");

                    Vector<String> rowV = new Vector<>();

                    rowV.add(resultSet.getString(1));
                    rowV.add(resultSet.getString(2) + "--" + resultSet.getString(3));
                    rowV.add(rs.getString(4));
                    rowV.add(rs.getString(6));
                    tableValues.add(rowV);

                    book = new JTable(tableValues, columnNames);/*将数据插入表格中*/

                }
                table1 = new JLabel("书籍编号");
                table1.setBounds(120, 45, 100, 25);
                table1.setForeground(Color.blue);
                add(table1);

                table2 = new JLabel("书籍名字");
                table2.setBounds(350, 45, 100, 25);
                table2.setForeground(Color.blue);
                add(table2);

                table3 = new JLabel("借书时间");
                table3.setBounds(550, 45, 100, 25);
                table3.setForeground(Color.blue);
                add(table3);

                table4 = new JLabel("剩余未归还量");
                table4.setBounds(770, 45, 100, 25);
                table4.setForeground(Color.blue);
                add(table4);
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
    
    @Override
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==bookReturn) {
            try {
				new BookReturn("还书",readId,name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }

        if (e.getSource()==back) {
            try {
				new BookCheckReader("图书信息",readId,name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }

        if(e.getSource()==refresh){
            try {
				new ReaderBorrowBooks("图书借阅情况",name,readId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }
    }
}
