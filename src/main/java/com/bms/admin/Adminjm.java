package com.bms.admin;

import com.bms.book.BookCheck;
import com.bms.reader.ReaderCheck;
import lombok.SneakyThrows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class Adminjm extends JFrame implements ActionListener{

    String name = null;

    private JLabel  welcome;
    private JLabel  choose;

    private JButton readerwh;
    private JButton bookwh;
    private JButton back;
    private JButton setinformation;
    private JButton exit;

    public Adminjm(String title,String name) {

        this.setTitle(title);
        this.setSize(400,350);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.name = name;

        init();

        this.setVisible(true);
    }


    public void init() {
        this.setLayout(null);//清空整个布局管理器

        welcome=new JLabel(name+",您好！");
        welcome.setBounds(130,10,150,25);
        add(welcome);

        choose=new JLabel("请选择您接下来要进行的操作：");
        choose.setBounds(5,70,200,25);
        add(choose);


        readerwh=new JButton("读者维护");
        readerwh.setBounds(85,120,90,40);
        readerwh.setForeground(Color.blue);
        add(readerwh);

        bookwh=new JButton("图书维护");
        bookwh.setBounds(203,120,90,40);
        bookwh.setForeground(Color.blue);
        add(bookwh);

        back=new JButton("返回");
        readerwh.setBounds(85,120,90,40);
        readerwh.setForeground(Color.blue);
        add(readerwh);

        setinformation=new JButton("基本参数设置 ");
        setinformation.setBounds(115,180,150,30);
        setinformation.setForeground(Color.blue);
        add(setinformation);

        exit=new JButton("退出系统 ");
        exit.setBounds(115,270,150,30);
        exit.setBackground(Color.red);
        add(exit);


        readerwh.addActionListener(this);
        bookwh.addActionListener(this);
        setinformation.addActionListener(this);
        exit.addActionListener(this);

    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource()==readerwh) {
            new ReaderCheck("读者维护",name);//跳转到目标窗口
            this.dispose();//关闭此窗口口
        }

        if (e.getSource()==bookwh) {
            new BookCheck("图书维护",name);//跳转到目标窗口
            this.dispose();//关闭此窗口口
        }

        if (e.getSource()==setinformation) {
            JOptionPane.showMessageDialog(getParent(), "此功能正在赶来的路上！@_@","提示" , JOptionPane.INFORMATION_MESSAGE);
        }//跳转到注册界面

        if (e.getSource()==exit) {
            this.dispose();
        }//关闭此窗口口

    }

}





