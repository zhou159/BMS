package com.zhou.bms2.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.system.UserInfo;
import com.zhou.bms2.view.ForgetView;
import com.zhou.bms2.view.MainView;
import com.zhou.bms2.view.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Administrator
 */
@FXMLController
public class LoginController implements Initializable {
    
    private final LoginService loginService;
    private final UserInfo userInfo;
    @FXML
    public Label register;
    @FXML
    public Label forget;
    @FXML
    private Label passwordTip;
    @FXML
    private Label nameTip;
    
    @FXML
    private TextField accountTextField;
    @FXML
    private PasswordField passwordTextField;
    
    
    
    public LoginController(LoginService loginService, UserInfo userInfo){
        this.loginService = loginService;
        this.userInfo = userInfo;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources){}
    
    /**
     * 登录按钮点击事件
     */
    @FXML
    protected void onLoginButtonClick(){
        String account = accountTextField.getText();
        String password = passwordTextField.getText();
        if (StrUtil.isBlank(account)) {
            nameTip.setText("请输入用户名！");
            return;
        }
        
        if (StrUtil.isBlank(password)) {
            passwordTip.setText("请输入密码！");
            return;
        }
        
        final Login login = new Login();
        login.setAccount(account);
        login.setPassword(password);
        final Login login1 = loginService.login(login);
        
        if (ObjectUtil.isNull(login1)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("账号密码错误，请重新输入！");
            alert.show();
        }else {
            userInfo.setUserId(login.getReaderId());
            BmsApplication.showView(MainView.class);
        }
    }
    
    /**
     * 账号、密码输入框监听器
     * 点击过后，会清除输入框下方提示内容
     */
    @FXML
    protected void textNameFieldClickListener(){
        nameTip.setText("");
    }
    
    @FXML
    protected void textPasswordFieldClickListener(){
        passwordTip.setText("");
    }
    
    @FXML
    protected void textRegisterFieldClickListener(){
        BmsApplication.showView(RegisterView.class);
    }
    
    @FXML
    protected void textForgetFieldClickListener(){
        BmsApplication.showView(ForgetView.class);
    }
    
    /**
     * 重置按钮点击事件
     */
    @FXML
    protected void onResetButtonClick(){
        accountTextField.setText("");
        passwordTextField.setText("");
        nameTip.setText("");
        passwordTip.setText("");
    }
}
