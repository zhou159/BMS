package com.bms.book;

import com.bms.dao.DatabaseConnect;
import com.bms.util.LogUtils;
import lombok.SneakyThrows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

@SuppressWarnings("serial")
public class BookAdd extends JFrame implements ActionListener{

    Connection connection = DatabaseConnect.getConnection();
    String name = null;

    private JLabel fill;
    private JLabel BnameLabel;
    private JLabel BpublishhouseLabel;
    private JLabel BauthorLabel;
    private JLabel BpriceLabel;
    private JLabel Binventory;

    private JTextField BnameTextField;
    private JTextField BpublishhouseTextField;
    private JTextField BauthorTextField;
    private JTextField BpriceTextField;
    private JTextField BinventoryTextField;

    private JButton confirm;
    private JButton exit;


    public BookAdd(String title,String name) throws SQLException {
        this.setTitle(title);
        this.setSize(300,450);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.name = name;

        init();

        this.setVisible(true);
    }


    public void init() {
        this.setLayout(null);//清空整个布局管理器

        fill=new JLabel("请输入以下信息：");
        fill.setBounds(95,35,150,25);
        fill.setBackground(Color.blue);
        add(fill);

        BnameLabel=new JLabel("书名：");
        BnameLabel.setBounds(30,80,80,40);
        add(BnameLabel);

        BpublishhouseLabel=new JLabel("出版社：");
        BpublishhouseLabel.setBounds(30,120,80,40);
        add(BpublishhouseLabel);

        BauthorLabel=new JLabel("作者 ：");
        BauthorLabel.setBounds(30,160,80,40);
        add(BauthorLabel);

        BpriceLabel=new JLabel("价格：");
        BpriceLabel.setBounds(30,200,80,40);
        add(BpriceLabel);

        Binventory=new JLabel("库存量 ：");
        Binventory.setBounds(30,240,80,40);
        add(Binventory);

        BnameTextField=new JTextField();
        BnameTextField.setBounds(95,90,150,25);
        add(BnameTextField);

        BpublishhouseTextField=new JTextField();
        BpublishhouseTextField.setBounds(95,130,150,25);
        add(BpublishhouseTextField);

        BauthorTextField=new JTextField();
        BauthorTextField.setBounds(95,170,150,25);
        add(BauthorTextField);

        BpriceTextField=new JTextField();
        BpriceTextField.setBounds(95,210,150,25);
        add(BpriceTextField);

        BinventoryTextField=new JTextField();
        BinventoryTextField.setBounds(95,250,150,25);
        add(BinventoryTextField);

        confirm=new JButton("确认信息");
        confirm.setBounds(30,335,85,30);
        confirm.setBackground(Color.blue);
        add(confirm);

        exit=new JButton("返回并查看信息 ");
        exit.setBounds(120,335,125,30);
        exit.setBackground(Color.red);
        add(exit);

        confirm.addActionListener(this);
        exit.addActionListener(this);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource()==confirm) {
            if (BnameTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "书名不能为空！");
            }
            else {
                try {
                    PreparedStatement pstm = connection.prepareStatement("insert into book (name,publishhouse,author,price,stock) values (?,?,?,?,?)");
                    pstm.setString(1,"《"+BnameTextField.getText().trim()+"》");
                    pstm.setString(2,BpublishhouseTextField.getText().trim());
                    pstm.setString(3,BauthorTextField.getText().trim());
                    pstm.setBigDecimal(4,new BigDecimal(BpriceTextField.getText().trim()));
                    pstm.setInt(5,Integer.valueOf(BinventoryTextField.getText().trim()).intValue());
                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "添加书籍成功！");

                    LogUtils.createLog(name+",新增了"+BinventoryTextField.getText().trim()+"本书籍:"+"《"+BnameTextField.getText().trim()+"》"+"--"+BauthorTextField.getText().trim());

                    new BookCheck("图书信息",name);
                    this.dispose();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

        if (e.getSource()==exit) {
            new BookCheck("图书信息",name);
            this.dispose();
        }
    }
}






