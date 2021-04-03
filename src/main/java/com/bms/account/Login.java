package com.bms.account;

import com.bms.admin.Adminjm;
import com.bms.dao.DatabaseConnect;
import com.bms.reader.Readerjm;
import lombok.SneakyThrows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Login extends JFrame implements ActionListener {

    String name = null;
    int readerId = 0;

    PreparedStatement pstm=null;
    Connection connection=null;

    private JLabel AaccountLabel;
    private JLabel  ApasswordLabel;
    private JTextField AaccountTextField;
    private JPasswordField ApasswordPasswordField;
    private JButton login;
    private JButton cancel;
    private JButton register;
    private JButton wjma;

    public Login(String title){

        this.setTitle(title);//设置窗口标题
        this.setSize(380,300);//设置窗口大小
        this.setResizable(false);//设置窗口大小是否可变
        this.setLocationRelativeTo(getOwner());//窗口出现位置,getOwner:正中间
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口就停止程序运行

        init();//初始化函数,加载各个组件

        this.setVisible(true);//是否可视化,一定要开启!!!
    }


    public void init() {
        this.setLayout(null);//清空整个布局管理器

        AaccountLabel=new JLabel("账号:");
        AaccountLabel.setBounds(75,35,50,25);
        add(AaccountLabel);

        ApasswordLabel=new JLabel("密码:");
        ApasswordLabel.setBounds(75,80,50,25);
        add(ApasswordLabel);

        AaccountTextField=new JTextField();
        AaccountTextField.setBounds(110,35,150,25);
        add(AaccountTextField);

        ApasswordPasswordField=new JPasswordField();
        ApasswordPasswordField.setBounds(110,80,150,25);
        add(ApasswordPasswordField);


        login=new JButton("登陆");
        login.setBounds(75,140,90,40);
        login.setBackground(Color.blue);
        add(login);

        cancel=new JButton("取消");
        cancel.setBounds(183,140,90,40);
        add(cancel);

        register=new JButton("点击我注册账户哟！ ");
        register.setBounds(111,200,150,30);
        register.setBackground(Color.yellow);
        add(register);

        wjma=new JButton("忘记密码？ ");
        wjma.setBounds(260,80,100,25);
        add(wjma);


        login.addActionListener(this);
        cancel.addActionListener(this);
        register.addActionListener(this);
        wjma.addActionListener(this);
    }

    @SneakyThrows
    @SuppressWarnings("unlikely-arg-type")
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource()==login) {
            if(AaccountTextField.getText().trim().equals("") || new String(ApasswordPasswordField.getPassword()).trim().equals("")) {
                JOptionPane.showMessageDialog(getParent(), "账号、密码不能为空！","提示" , JOptionPane.INFORMATION_MESSAGE);
            }else {
                connection = DatabaseConnect.getConnection();
                pstm = connection.prepareStatement("select * from reader where account=? and password=?");

                pstm.setString(1,AaccountTextField.getText().trim());
                pstm.setString(2,new String(ApasswordPasswordField.getPassword()).trim());

                ResultSet rs=pstm.executeQuery();
                if(!rs.next()) {
                    JOptionPane.showMessageDialog(getParent(), "请输入正确的账号密码！","提示" , JOptionPane.INFORMATION_MESSAGE);
                }else {
                    if(rs.getString(2)==null){
                        readerId = rs.getInt(1);
                        new LoginInfoUpdate("读者信息绑定",name,readerId);
                        this.dispose();
                    }else {
                        if(rs.getString(8).equals("读者")){
                            readerId = rs.getInt(1);
                            name= rs.getString(8) + ":" + rs.getString(2);
                            new Readerjm("读者界面",name,readerId);
                            this.dispose();
                        }else {
                            name = rs.getString(8) + ":" + rs.getString(2);

                            new Adminjm("管理员界面",name);
                            this.dispose();
                        }
                    }
                }
            }

        }//判断管理员还是读者、默认只有admin一个管理员，

        if (e.getSource()==cancel) {
            AaccountTextField.setText(null);
            ApasswordPasswordField.setText(null);
            this.dispose();
        }

        if (e.getSource()==register) {
            new Register("注册账户");
            this.dispose();
        }//跳转到注册界面

        if (e.getSource()==wjma) {
            JOptionPane.showMessageDialog(getParent(), "忘记密码，我们也不能帮你哦，嘻嘻","提示" , JOptionPane.INFORMATION_MESSAGE);
        }//关闭此窗口口
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new Login("图书管理系统");
    }

}
