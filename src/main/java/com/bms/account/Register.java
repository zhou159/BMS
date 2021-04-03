package com.bms.account;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bms.dao.DatabaseConnect;




public class Register extends JFrame implements ActionListener {



    private JLabel  newAaccountLabel;
    private JLabel  ApasswordLabel;
    private JTextField newAaccountTextField;
    private JPasswordField ApasswordPasswordField;
    private JButton confirm;
    private JButton cancel;


    public Register(String title) {
        this.setTitle(title);
        this.setSize(400,250);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());

        init();

        this.setVisible(true);
    }

    public void init() {
        this.setLayout(null);//清空整个布局管理器

        newAaccountLabel=new JLabel("新建账号:");
        newAaccountLabel.setBounds(75,27,60,40);
        add(newAaccountLabel);

        ApasswordLabel=new JLabel("创建密码:");
        ApasswordLabel.setBounds(75,72,60,40);
        add(ApasswordLabel);

        newAaccountTextField=new JTextField();
        newAaccountTextField.setBounds(135,35,150,25);
        add(newAaccountTextField);

        ApasswordPasswordField=new JPasswordField();
        ApasswordPasswordField.setBounds(135,80,150,25);
        add(ApasswordPasswordField);

        confirm=new JButton("确认");
        confirm.setBounds(85,140,90,40);
        add(confirm);

        cancel=new JButton("取消");
        cancel.setBounds(190,140,90,40);
        add(cancel);

        confirm.addActionListener(this);
        cancel.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        DatabaseConnect db = new DatabaseConnect();
        ResultSet rs;
        if (e.getSource()==confirm) {
            try {
                Connection connection = DatabaseConnect.getConnection();
                PreparedStatement pstm = connection.prepareStatement("select * from reader where account=?");
                PreparedStatement pstm2 = connection.prepareStatement("insert into reader (account,password) values (?,?)");

                if (newAaccountTextField.getText().trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "账号不能为空!");
                }
                else if (new String(ApasswordPasswordField.getPassword()).trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "密码不能为空!");
                }
                else {
                    pstm.setString(1,newAaccountTextField.getText().trim());
                    ResultSet set = pstm.executeQuery();
                    if (set.next()) {
                        JOptionPane.showMessageDialog(null, "此账户已经存在，请重新输入用户名!");
                    }
                    else {
                        pstm2.setString(1,newAaccountTextField.getText().trim());
                        pstm2.setString(2,new String(ApasswordPasswordField.getPassword()).trim());

                        pstm2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "注册成功!");
                        new Login("图书管理系统");
                        this.dispose();
                    }
                }
            }
            catch (SQLException sqle) {
                System.out.println(sqle.toString());
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        if (e.getSource()==cancel) {
            newAaccountTextField.setText(null);
            ApasswordPasswordField.setText(null);
            new Login("图书管理系统");
            this.dispose();
        }
    }
}
