package com.bms.reader;

import com.bms.dao.DatabaseConnect;
import lombok.SneakyThrows;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;


@SuppressWarnings("serial")
public class ReaderModification extends JFrame implements ActionListener{

    /**

     *
     */
    Connection connection = DatabaseConnect.getConnection();
    String name = null;

    private JLabel  ridLabel;
    private JLabel  fill;
    private JLabel RnameLabel;
    private JLabel RageLabel;
    private JLabel RsexLabel;
    private JLabel RamountLabel;
    private JLabel RforfeitLabel;
    private JLabel RprofessionLabel;

    private JTextField ridTextField;
    private JTextField RnameTextField;
    private JTextField RageTextField;
    private JTextField RsexTextField;
    private JTextField RamountTextField;
    private JTextField RforfeitTextField;
    private JTextField RprofessionTextField;

    private JButton select;
    private JButton confirm;
    private JButton back;


    public ReaderModification(String title,String name) throws SQLException {

        this.setTitle(title);
        this.setSize(300,450);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭窗口就停止程序运行

        this.name = name;

        init();

        this.setVisible(true);
    }


    public void init() {
        this.setLayout(null);//清空整个布局管理器

        ridLabel=new JLabel("请输入你想要修改的读者ID：");
        ridLabel.setBounds(25,5,170,25);
        ridLabel.setForeground(Color.blue);
        add(ridLabel);

        fill=new JLabel("请输入你要修改的信息：");
        fill.setBounds(90,55,150,25);
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

        RprofessionLabel=new JLabel("职业 ：");
        RprofessionLabel.setBounds(30,200,80,40);
        add(RprofessionLabel);

        ridTextField=new JTextField();
        ridTextField.setBounds(25,30,150,25);
        add(ridTextField);

        RnameTextField=new JTextField();
        RnameTextField.setBounds(95,90,150,25);
        add(RnameTextField);

        RageTextField=new JTextField();
        RageTextField.setBounds(95,130,150,25);
        add(RageTextField);

        RsexTextField=new JTextField();
        RsexTextField.setBounds(95,170,150,25);
        add(RsexTextField);

        RprofessionTextField=new JTextField();
        RprofessionTextField.setBounds(95,210,150,25);
        add(RprofessionTextField);

        confirm=new JButton("确认修改");
        confirm.setBounds(30,335,85,30);
        confirm.setBackground(Color.blue);
        add(confirm);

        select=new JButton("查询");
        select.setBounds(190,30,85,25);
        add(select);

        back=new JButton("返回");
        back.setBounds(160,335,85,30);
        add(back);

        select.addActionListener(this);
        confirm.addActionListener(this);
        back.addActionListener(this);
    }

    @SneakyThrows
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (e.getSource()==select) {
            try{
                PreparedStatement pstm = connection.prepareStatement("select * from reader where id=?");
                pstm.setInt(1,Integer.valueOf(ridTextField.getText().trim()).intValue());
                ResultSet resultSet = pstm.executeQuery();

                if (resultSet.next()){
                    RnameTextField.setText(resultSet.getString(2));
                    RageTextField.setText(resultSet.getString(3));
                    RsexTextField.setText(resultSet.getString(4));
                    RprofessionTextField.setText(resultSet.getString(5));

                    //多次查询时，如果上一次没有结果，清楚上一次遗留的信息
                    fill.setText("请输入你要修改的信息：");
                    fill.setForeground(Color.blue);
                }else {
                    fill.setText("未找到该读者");
                    fill.setForeground(Color.red);

                    //多次查询时，如果下一次没有结果，清楚上一次遗留的信息
                    RnameTextField.setText("");
                    RageTextField.setText("");
                    RsexTextField.setText("");
                    RprofessionTextField.setText("");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        if (e.getSource()==confirm) {
            try {
                PreparedStatement pstm2 = connection.prepareStatement("update reader set name=?,age=?,sex=?,profession=? where id=?");
                pstm2.setString(1, RnameTextField.getText().trim());
                pstm2.setInt(2, Integer.valueOf(RageTextField.getText().trim()).intValue());
                pstm2.setString(3, RsexTextField.getText().trim());
                pstm2.setString(4, RprofessionTextField.getText().trim());
                pstm2.setInt(5,Integer.valueOf(ridTextField.getText().trim()).intValue());
                pstm2.executeUpdate();

                new ReaderCheck("读者信息",name);
                this.dispose();
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "请输入正确的数");
                System.out.println(ex);
            }
        }

        if(e.getSource()==back){
            new ReaderCheck("读者信息",name);
            this.dispose();
        }
    }
}






