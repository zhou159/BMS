package com.zhou.bms2.controller;

import com.zhou.bms2.entity.Login;
import com.zhou.bms2.service.LoginService;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private TextField accountTextField;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button btnLogin;
    
    public LoginController(LoginService loginService){this.loginService = loginService;}
    
    @Override
    public void initialize(URL location, ResourceBundle resources){}
    
    @FXML
    protected void onLoginButtonClick(){
        String account = accountTextField.getText();
        String password = passwordTextField.getText();
        final Login login = new Login();
        login.setAccount(account);
        login.setPassword(password);
        final Boolean login1 = loginService.login(login);
        if (login1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("登录成功！");
            alert.show();
        }
    }
    
    @FXML
    protected void onResetButtonClick(){
        accountTextField.setText("");
        passwordTextField.setText("");
    }
}
