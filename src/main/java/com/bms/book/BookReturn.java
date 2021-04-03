package com.bms.book;


import com.bms.dao.DatabaseConnect;
import com.bms.util.LogUtils;
import lombok.SneakyThrows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BookReturn extends JFrame implements ActionListener{

    Connection connection = DatabaseConnect.getConnection();

    int readId = 0;
    String name = null;

    private JLabel  bidLabel;
    private JLabel  amountLabel;
    private JTextField bidTextField;
    private JTextField amountTextField;
    private JButton confirm;
    private JButton back;

    public BookReturn(String title,int readId,String name) throws SQLException {

        this.setTitle(title);
        this.setSize(320,220);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());

        init();

        this.setVisible(true);

        this.readId = readId;
        this.name = name;
    }
    public void init() {
        this.setLayout(null);//清空整个布局管理器


        bidLabel=new JLabel("请输入被还书籍的编号：");
        bidLabel.setBounds(30,20,150,25);
        add(bidLabel);

        amountLabel=new JLabel("请输入还书数量：");
        amountLabel.setBounds(30,70,130,25);
        add(amountLabel);


        bidTextField=new JTextField();
        bidTextField.setBounds(190,20,90,25);
        add(bidTextField);

        amountTextField=new JTextField();
        amountTextField.setBounds(190,70,90,25);
        add(amountTextField);


        confirm=new JButton("确认信息");
        confirm.setBounds(30,140,90,25);
        add(confirm);

        back=new JButton("返回");
        back.setBounds(190,140,90,25);
        add(back);

        confirm.addActionListener(this);
        back.addActionListener(this);

    }
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {


        if (e.getSource()==confirm) {
            if (e.getSource()==confirm) {
                if (bidTextField.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "书籍编号不能为空！");
                    }
                else if (amountTextField.getText().trim().equals("")) {
                        JOptionPane.showMessageDialog(null, "还书数量不能为空！");
                    }
                else {
                    try {
                        //根据前面传递的用户id和输入的书籍id，在借书表中查询是否该用户借过这本书!
                        PreparedStatement pstm = connection.prepareStatement("select * from br where reader_id=? and book_id=?");
                        pstm.setInt(1,readId);
                        pstm.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                        ResultSet resultSet = pstm.executeQuery();
                        if(!resultSet.next()){
                            JOptionPane.showMessageDialog(null, "你好像还没有借过这本书@_@!");
                        }else {
                            int amount = resultSet.getInt(6);
                            if(Integer.valueOf(amountTextField.getText().trim()).intValue()<=amount ){
                                //更新借书表中的相关信息:借书量，还书时间
                                PreparedStatement pstm2 = connection.prepareStatement("update br set returntime=?,amount=? where reader_id=? and book_id=?");
                                pstm2.setString(1,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
                                pstm2.setInt(2,amount - Integer.valueOf(amountTextField.getText().trim()).intValue());
                                pstm2.setInt(3,readId);
                                pstm2.setInt(4,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                pstm2.executeUpdate();

                                //通过输入的书籍id查询相关信息
                                PreparedStatement pstm4 = connection.prepareStatement("select * from book where id=?");
                                pstm4.setInt(1,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                ResultSet resultSet1 = pstm4.executeQuery();

                                if(!resultSet1.next()){
                                }else {
                                    //获取该书籍库存量
                                    int stock = resultSet1.getInt(5);

                                    //通过输入的书籍id修改库存量
                                    PreparedStatement pstm3 = connection.prepareStatement("update book set stock=? where id=?");
                                    pstm3.setInt(1,stock+Integer.valueOf(amountTextField.getText().trim()).intValue());
                                    pstm3.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                    pstm3.executeUpdate();

                                    LogUtils.createLog(name+",还回了"+amountTextField.getText().trim()+"本图书:"+resultSet1.getString(2)+"--"+resultSet1.getString(3));

                                    JOptionPane.showMessageDialog(null, "还书成功！");
                                    new BookCheckReader("图书信息",readId,name);
                                    this.dispose();
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "你好像还没有借过这么多书哦,请减少还书数量!");
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.toString());
                    }
                }
            }
        }

        if (e.getSource()==back) {
            new BookCheckReader("图书信息",readId,name);
            this.dispose();
        }
    }
}
