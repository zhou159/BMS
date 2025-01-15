package com.zhou.bms2.controller;

import cn.hutool.cache.Cache;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.system.UserInfo;
import com.zhou.bms2.util.AlertUtil;
import com.zhou.bms2.view.AdminMainView;
import com.zhou.bms2.view.ForgetView;
import com.zhou.bms2.view.MainView;
import com.zhou.bms2.view.RegisterView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Administrator
 */
@RequiredArgsConstructor
@FXMLController
public class LoginController implements Initializable {

    private final LoginService loginService;

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

    @Resource(name = "commonCache")
    private Cache<String, UserInfo> cache;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * 登录按钮点击事件
     */
    @FXML
    protected void onLoginButtonClick() {
        String account = accountTextField.getText();
        String password = passwordTextField.getText();
        if (!this.checkValue()) {
            return;
        }

        UserInfo loginUser = loginService.login(account, password);

        if (ObjectUtil.isNull(loginUser)) {
            AlertUtil.showError("账号密码错误，请重新输入！");
            return;
        }

        cache.put("userInfo", loginUser);

        // 没有找到角色，则使用学生角色
        if (StrUtil.isNotBlank(loginUser.getRoleName()) && "admin".equals(loginUser.getRoleName())) {
            BmsApplication.showView(AdminMainView.class);
            return;
        }
        BmsApplication.showView(MainView.class);
    }

    private boolean checkValue() {
        if (StrUtil.isBlank(accountTextField.getText())) {
            nameTip.setText("请输入用户名！");
            return false;
        }

        if (StrUtil.isBlank(passwordTextField.getText())) {
            passwordTip.setText("请输入密码！");
            return false;
        }
        return true;
    }

    /**
     * 账号、密码输入框监听器
     * 点击过后，会清除输入框下方提示内容
     */
    @FXML
    protected void textNameFieldClickListener() {
        nameTip.setText("");
    }

    @FXML
    protected void textPasswordFieldClickListener() {
        passwordTip.setText("");
    }

    @FXML
    protected void textRegisterFieldClickListener() {
        BmsApplication.showView(RegisterView.class);
    }

    @FXML
    protected void textForgetFieldClickListener() {
        BmsApplication.showView(ForgetView.class);
    }

    /**
     * 重置按钮点击事件
     */
    @FXML
    protected void onResetButtonClick() {
        accountTextField.setText("");
        passwordTextField.setText("");
        nameTip.setText("");
        passwordTip.setText("");
    }
}
