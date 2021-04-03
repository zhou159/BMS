package com.bms.reader;

import com.bms.dao.DatabaseConnect;
import lombok.SneakyThrows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class ReaderAdd extends JFrame implements ActionListener{

    Connection connection = DatabaseConnect.getConnection();
    String name = null;

    private JLabel  fill;
    private JLabel RnameLabel;
    private JLabel RageLabel;
    private JLabel RsexLabel;
    private JLabel RamountLabel;
    private JLabel RprofessionLabel;
    private JTextField RnameTextField;
    private JTextField RageTextField;
    private JTextField RsexTextField;
    private JTextField RamountTextField;
    private JTextField RprofessionTextField;
    private JButton confirm;
    private JButton exit;


    public ReaderAdd(String title,String name) throws SQLException {

        this.setTitle(title);
        this.setSize(300,450);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());

        this.name = name;

        init();

        this.setVisible(true);
    }


    public void init() {
        this.setLayout(null);//清空整个布局管理器

        fill=new JLabel("请输入以下信息：");
        fill.setBounds(95,35,150,25);
        fill.setForeground(Color.blue);
        add(fill);

        RnameLabel=new JLabel("姓名：");
        RnameLabel.setBounds(30,80,80,40);
        add(RnameLabel);

        RageLabel=new JLabel("年龄：");
        RageLabel.setBounds(30,120,80,40);
        add(RageLabel);

        RsexLabel=new JLabel("性别 ：");
        RsexLabel.setBounds(30,160,80,40);
        add(RsexLabel);

        RamountLabel=new JLabel("借书量 ：");
        RamountLabel.setBounds(30,200,80,40);
        add(RamountLabel);


        RprofessionLabel=new JLabel("职业 ：");
        RprofessionLabel.setBounds(30,240,80,40);
        add(RprofessionLabel);

        RnameTextField=new JTextField();
        RnameTextField.setBounds(95,90,150,25);
        add(RnameTextField);

        RageTextField=new JTextField();
        RageTextField.setBounds(95,130,150,25);
        add(RageTextField);

        RsexTextField=new JTextField();
        RsexTextField.setBounds(95,170,150,25);
        add(RsexTextField);

        RamountTextField=new JTextField();
        RamountTextField.setBounds(95,210,150,25);
        add(RamountTextField);

        RprofessionTextField=new JTextField();
        RprofessionTextField.setBounds(95,250,150,25);
        add(RprofessionTextField);

        confirm=new JButton("确认无误");
        confirm.setBounds(20,335,120,30);
        confirm.setBackground(Color.blue);
        add(confirm);

        exit=new JButton("返回");
        exit.setBounds(140,335,125,30);
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
            if (RnameTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "姓名不能为空！");
            }
            else if (RprofessionTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "读者职业不能为空！");
            }
            else {
                try {
                    PreparedStatement pstm = connection.prepareStatement("insert into reader (name,age,sex,amount,forfeit,profession) values (?,?,?,?,?,?)");

                    pstm.setString(1,RnameTextField.getText().trim());
                    pstm.setInt(2,Integer.valueOf(RageTextField.getText().trim()).intValue());
                    pstm.setString(3,RsexTextField.getText().trim());
                    pstm.setInt(4,Integer.valueOf(RamountTextField.getText().trim()).intValue());
                    pstm.setString(6,RprofessionTextField.getText().trim());

                    pstm.executeUpdate();
                    JOptionPane.showMessageDialog(null, "添加读者成功！");
                    new ReaderCheck("读者信息",name);
                    this.dispose();
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

        if (e.getSource()==exit) {
            new ReaderCheck("读者信息",name);
            this.dispose();
        }
    }
}





