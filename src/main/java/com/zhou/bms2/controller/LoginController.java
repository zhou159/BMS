package com.zhou.bms2.controller;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.system.UserInfo;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@FXMLController
public class LoginController implements Initializable {
    
    private final LoginService loginService;
    private final UserInfo userInfo;
    @FXML
    private Label passwordTip;
    @FXML
    private Label nameTip;
    
    @FXML
    private TextField accountTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button btnLogin;
    
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
        }
    }
    
    /**
     * 账号、密码输入框监听器
     * 点击过后，会清除输入框下方提示内容
     */
    @FXML
    protected void textFieldClickListener(){
        nameTip.setText("");
        passwordTip.setText("");
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
