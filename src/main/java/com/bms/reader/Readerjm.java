package com.bms.reader;

import com.bms.book.BookCheckReader;
import lombok.SneakyThrows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


@SuppressWarnings("serial")
public class Readerjm extends JFrame implements ActionListener{

    String name=null;
    int readId=0;

    private JLabel  welcome;
    private JLabel  choose;

    private JButton bookcheck;
    private JButton exit;

    public Readerjm(String title,String name,int readId){
        this.name = name;
        this.readId = readId;

        this.setTitle(title);
        this.setSize(400,350);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        init();

        this.setVisible(true);
    }

    public void init() {
        this.setLayout(null);//清空整个布局管理器

        welcome=new JLabel(name+",你好!");
        welcome.setBounds(150,10,90,25);
        add(welcome);

        choose=new JLabel("请选择你接下来要进行的操作：");
        choose.setBounds(5,70,200,25);
        add(choose);

        bookcheck=new JButton("图书信息查看 ");
        bookcheck.setBounds(115,120,150,30);
        bookcheck.setForeground(Color.blue);
        add(bookcheck);

        exit=new JButton("退出系统 ");
        exit.setBounds(115,230,150,30);
        exit.setBackground(Color.red);
        add(exit);

        bookcheck.addActionListener(this);
        exit.addActionListener(this);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource()==bookcheck) {
            new BookCheckReader("图书信息",readId,name);
            this.dispose();
        }

        if (e.getSource()==exit) {
            System.exit(EXIT_ON_CLOSE);
        }

    }
}




