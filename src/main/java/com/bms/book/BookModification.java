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
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

@SuppressWarnings("serial")
public class BookModification extends JFrame implements ActionListener{

    Connection connect = DatabaseConnect.getConnection();
    String name = null;
    int bookId = 0;

    private JLabel  BidLabel;
    private JLabel  fill;
    private JLabel BnameLabel;
    private JLabel BpublishhouseLabel;
    private JLabel BauthorLabel;
    private JLabel BpriceLabel;
    private JLabel Binventory;

    private JTextField BidTextField;
    private JTextField BnameTextField;
    private JTextField BpublishhouseTextField;
    private JTextField BauthorTextField;
    private JTextField BpriceTextField;
    private JTextField BstockTextField;

    private JButton select;
    private JButton confirm;
    private JButton back;

    public BookModification(String title,String name,int bookId) throws SQLException {

        this.setTitle(title);
        this.setSize(300,450);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.name = name;
        this.bookId = bookId;

        init();

        this.setVisible(true);
    }

    public void init() throws SQLException {
        this.setLayout(null);//清空整个布局管理器
        
        BidLabel=new JLabel("请输入你想要修改的图书ID：");
        BidLabel.setBounds(25,5,170,25);
        BidLabel.setForeground(Color.blue);
        add(BidLabel);

        fill=new JLabel("请输入你要修改的信息：");
        fill.setBounds(90,55,150,25);
        fill.setForeground(Color.blue);
        add(fill);

        BnameLabel=new JLabel("书名：");
        BnameLabel.setBounds(30,80,80,40);
        add(BnameLabel);

        BpublishhouseLabel=new JLabel("出版社：");
        BpublishhouseLabel.setBounds(30,120,80,40);
        add(BpublishhouseLabel);

        BauthorLabel=new JLabel("作者：");
        BauthorLabel.setBounds(30,160,80,40);
        add(BauthorLabel);

        BpriceLabel=new JLabel("价格 ：");
        BpriceLabel.setBounds(30,200,80,40);
        add(BpriceLabel);

        Binventory=new JLabel("库存量 ：");
        Binventory.setBounds(30,240,80,40);
        add(Binventory);

        BidTextField=new JTextField();
        BidTextField.setBounds(25,30,150,25);
        BidTextField.setText(String.valueOf(bookId));
        add(BidTextField);

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

        BstockTextField=new JTextField();
        BstockTextField.setBounds(95,250,150,25);
        add(BstockTextField);

        select=new JButton("查询");
        select.setBounds(190,30,85,25);
        add(select);

        confirm=new JButton("确认修改！");
        confirm.setBounds(30,335,110,30);
        confirm.setBackground(Color.blue);
        add(confirm);

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
                PreparedStatement pstm2 = connect.prepareStatement("select * from book where id=?");
                pstm2.setInt(1,Integer.valueOf(BidTextField.getText().trim()).intValue());

                ResultSet resultSet = pstm2.executeQuery();

                if (resultSet.next()){
                    BnameTextField.setText(resultSet.getString(2));
                    BauthorTextField.setText(resultSet.getString(3));
                    BpriceTextField.setText(resultSet.getString(4));
                    BstockTextField.setText(resultSet.getString(5));
                    BpublishhouseTextField.setText(resultSet.getString(6));

                    //多次查询时，如果上一次没有结果，清除上一次遗留的信息
                    fill.setText("请输入你要修改的信息：");
                    fill.setForeground(Color.blue);
                }else {
                    fill.setText("未找到该书籍");
                    fill.setForeground(Color.red);

                    //多次查询时，如果下一次没有结果，清除上一次遗留的信息
                    BnameTextField.setText("");
                    BauthorTextField.setText("");
                    BpriceTextField.setText("");
                    BstockTextField.setText("");
                    BpublishhouseTextField.setText("");
                }
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex);
            }
        }

        if (e.getSource()==confirm) {
            try {
                PreparedStatement pstm4 = connect.prepareStatement("select * from book where id=?");
                pstm4.setInt(1,Integer.valueOf(BidTextField.getText().trim()).intValue());
                ResultSet resultSet1 = pstm4.executeQuery();

                PreparedStatement pstm = connect.prepareStatement("update book set name=?,publishhouse=?,author=?,price=?,stock=? where id=?");
                pstm.setString(1,BnameTextField.getText().trim());
                pstm.setString(2,BpublishhouseTextField.getText().trim());
                pstm.setString(3,BauthorTextField.getText().trim());
                pstm.setBigDecimal(4,new BigDecimal(BpriceTextField.getText().trim()));
                pstm.setInt(5, Integer.valueOf(BstockTextField.getText().trim()).intValue());
                pstm.setInt(6,Integer.valueOf(BidTextField.getText().trim()).intValue());
                pstm.executeUpdate();

                if(resultSet1.next()){
                    LogUtils.createLog(name+",更新了书籍信息:"
                    +"书籍名字:"+resultSet1.getString(2)+"->"+BnameTextField.getText().trim()+";"
                    +"书籍作者:"+resultSet1.getString(3)+"->"+BauthorTextField.getText().trim()+";"
                    +"书籍价格:"+resultSet1.getString(4)+"->"+BpriceTextField.getText().trim()+";"
                    +"书籍库存:"+resultSet1.getString(5)+"->"+BstockTextField.getText().trim()+";"
                    +"书籍出版社:"+resultSet1.getString(6)+"->"+BpublishhouseTextField.getText().trim());
                }
                JOptionPane.showMessageDialog(null, "更新书籍信息成功！");

                new BookCheck("图书信息",name);
                this.dispose();
            }
            catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
        if (e.getSource()==back) {
            try {
				new BookCheck("图书信息",name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }
    }
}






