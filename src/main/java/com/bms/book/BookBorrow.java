package com.bms.book;



import com.bms.dao.DatabaseConnect;
import com.bms.util.LogUtils;
import lombok.SneakyThrows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

import javax.swing.*;

public class BookBorrow extends JFrame implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int readId = 0;
    String name = null;

    Connection connection = DatabaseConnect.getConnection();

    private JLabel  bidLabel;
    private JLabel  amountLabel;
    private JTextField bidTextField;
    private JTextField amountTextField;
    private JButton confirm;
    private JButton back;

    public BookBorrow(String title,int readId,String name) throws SQLException {

        this.setTitle(title);
        this.setSize(320,240);
        this.setResizable(false);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        init();

        this.setVisible(true);

        this.readId = readId;
        this.name = name;
    }
    public void init() {
        this.setLayout(null);//清空整个布局管理器

        bidLabel=new JLabel("请输入被借书籍的编号：");
        bidLabel.setBounds(30,20,150,25);
        add(bidLabel);

        amountLabel=new JLabel("请输入借书数量：");
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
            if (bidTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "书籍编号不能为空！");
            }
            else if (amountTextField.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "借书数量不能为空！");
            }
            else {
                try {
                    //通过输入的书籍id查询书籍,存在则进行下一步操作,不存在则输出错误信息
                    PreparedStatement pstm2 = connection.prepareStatement("select * from book where id=?");
                    pstm2.setInt(1,Integer.valueOf(bidTextField.getText().trim()).intValue());
                    ResultSet resultSet = pstm2.executeQuery();
                    if(!resultSet.next()){
                        JOptionPane.showMessageDialog(null, "书籍不存在，请重新输入!");
                    }else {
                        //查询借书表中该用户是否借过这本书
                        PreparedStatement pstm4 = connection.prepareStatement("select * from br where reader_id=? and book_id=?");
                        pstm4.setInt(1,readId);
                        pstm4.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                        ResultSet resultSet1 = pstm4.executeQuery();

                        //表里有记录
                        if(resultSet1.next()){
                            if(resultSet.getInt(5) < Integer.valueOf(amountTextField.getText().trim()).intValue() || resultSet.getInt(5)<=0){
                                JOptionPane.showMessageDialog(null, "该书数量少于你所借的数量,请更换书籍或减少数量!");
                            }else {
                                PreparedStatement pstm5 = connection.prepareStatement("update br set amount=?,borrowtime=? where id=?");
                                pstm5.setInt(1,Integer.valueOf(amountTextField.getText().trim()).intValue() + resultSet1.getInt(6));
                                pstm5.setString(2,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
                                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
                                pstm5.setInt(3,resultSet1.getInt(1));
                                pstm5.executeUpdate();

                                PreparedStatement pstm6 = connection.prepareStatement("update book set stock=? where id=?");
                                pstm6.setInt(1,resultSet.getInt(5) - Integer.valueOf(amountTextField.getText().trim()).intValue());
                                pstm6.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                pstm6.executeUpdate();

                                LogUtils.createLog(name+",借走了"+amountTextField.getText().trim()+"本图书:"+resultSet.getString(2)+"--"+resultSet.getString(3));

                                JOptionPane.showMessageDialog(null, "借书成功！");
                                new BookCheckReader("图书信息",readId,name);
                                this.dispose();
                            }
                        //表里没记录
                        }else {
                            //查询出书籍库存量
                            if(resultSet.getInt(5) < Integer.valueOf(amountTextField.getText().trim()).intValue() || resultSet.getInt(5)<=0){
                                JOptionPane.showMessageDialog(null, "该书数量少于你所借的数量,请更换书籍或减少数量!");
                            }else {
                                PreparedStatement pstm = connection.prepareStatement("insert into br (reader_id,book_id,borrowtime,amount) values (?,?,?,?)");
                                pstm.setInt(1,readId);
                                pstm.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                pstm.setString(3,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date().getTime()));
                                pstm.setInt(4,Integer.valueOf(amountTextField.getText().trim()).intValue());
                                pstm.executeUpdate();

                                PreparedStatement pstm3 = connection.prepareStatement("update book set stock=? where id=?");
                                pstm3.setInt(1,resultSet.getInt(5) - Integer.valueOf(amountTextField.getText().trim()).intValue());
                                pstm3.setInt(2,Integer.valueOf(bidTextField.getText().trim()).intValue());
                                pstm3.executeUpdate();

                                LogUtils.createLog(name+",借走了"+amountTextField.getText().trim()+"本图书:"+resultSet.getString(2)+"--"+resultSet.getString(3));

                                JOptionPane.showMessageDialog(null, "借书成功！");
                                new BookCheckReader("图书信息",readId,name);
                                this.dispose();
                            }
                        }

                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex);
                }
            }
        }

        if (e.getSource()==back) {
            try {
				new BookCheckReader("图书信息",readId,name);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            this.dispose();
        }
    }
}
