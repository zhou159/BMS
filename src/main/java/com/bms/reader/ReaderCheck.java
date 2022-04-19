package com.bms.reader;

import com.bms.admin.Adminjm;
import com.bms.dao.DatabaseConnect;
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

public class ReaderCheck extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Connection connection = DatabaseConnect.getConnection();

    JTable reader = new JTable();

    String name = null;

    private JButton readerModification;
    private JButton readerDelete;
    private JButton back;
    private JButton refresh;
    private JLabel table1;
    private JLabel table2;
    private JLabel table3;
    private JLabel table4;
    private JLabel table5;
    private JLabel table6;
    
    public ReaderCheck(String title,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(970,700);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.name = name;

        init();

        this.setVisible(true);

    }
    public void init() throws SQLException {
        PreparedStatement pstm = connection.prepareStatement("select * from reader where type = '读者'");
        ResultSet rs = pstm.executeQuery();

        Vector<String> columnNames = new Vector<>();
        columnNames.add("编号");
        columnNames.add("姓名");
        columnNames.add("年龄");
        columnNames.add("性别");
        columnNames.add("职业");
        columnNames.add("账户类型");

        Vector<Vector<String>> tableValues = new Vector<>();
        while (rs.next()){
            Vector<String> rowV = new Vector<>();
            rowV.add(rs.getString(1));
            rowV.add(rs.getString(2));
            rowV.add(rs.getString(3));
            rowV.add(rs.getString(4));
            rowV.add(rs.getString(5));
            rowV.add(rs.getString(8));
            tableValues.add(rowV);

            reader = new JTable(tableValues,columnNames);
        }

        reader.setPreferredScrollableViewportSize(new Dimension(900, 550));
        JScrollPane s = new JScrollPane(reader);
        getContentPane().add(s, BorderLayout.CENTER);
        reader.revalidate();
        this.setLayout(null);//清空整个布局管理器

        reader.setBounds(15, 75, 920, 500);
        add(reader);

        table1=new JLabel("读者编号");
        table1.setBounds(70,50,90,25);
        table1.setForeground(Color.blue);
        add(table1);

        table2=new JLabel("读者姓名");
        table2.setBounds(220,50,90,25);
        table2.setForeground(Color.blue);
        add(table2);

        table3=new JLabel("读者年龄");
        table3.setBounds(360,50,90,25);
        table3.setForeground(Color.blue);
        add(table3);

        table4=new JLabel("读者性别");
        table4.setBounds(525,50,90,25);
        table4.setForeground(Color.blue);
        add(table4);

        table5=new JLabel("读者职业");
        table5.setBounds(670,50,90,25);
        table5.setForeground(Color.blue);
        add(table5);

        table6=new JLabel("读者账户类型");
        table6.setBounds(820,50,90,25);
        table6.setForeground(Color.blue);
        add(table6);

        readerModification=new JButton("修改信息");
        readerModification.setBounds(150,15,90,25);
        add(readerModification);

        readerDelete=new JButton("删除信息");
        readerDelete.setBounds(300,15,90,25);
        readerDelete.setBackground(Color.red);
        add(readerDelete);

        back=new JButton("返回");
        back.setBounds(800,615,90,25);
        back.setBackground(Color.blue);
        add(back);

        refresh=new JButton("刷新");
        refresh.setBounds(850,15,70,25);
        add(refresh);

        readerModification.addActionListener(this);
        readerDelete.addActionListener(this);
        back.addActionListener(this);
        refresh.addActionListener(this);
    }
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==readerModification) {
            try {
				new ReaderModification("修改读者信息",name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }

        if(e.getSource()==readerDelete){
            int count = reader.getSelectedRow();
            String id = reader.getValueAt(count,0).toString();
            PreparedStatement pstm;
			try {
				pstm = connection.prepareStatement("delete from reader where id=?");
				pstm.setInt(1,Integer.valueOf(id).intValue());
	            pstm.executeUpdate();
	            new ReaderCheck("读者维护",name);
	            this.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        }

        if (e.getSource()==back) {
            new Adminjm("读者维护",name);
            this.dispose();
        }

        if(e.getSource()==refresh){
            try {
				new ReaderCheck("读者信息",name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }
    }
}

