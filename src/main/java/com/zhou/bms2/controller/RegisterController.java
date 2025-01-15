package com.zhou.bms2.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.system.enums.SourceEnum;
import com.zhou.bms2.system.util.AlertUtil;
import com.zhou.bms2.system.util.ImageUtil;
import com.zhou.bms2.view.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

/**
 * @author zhouxiong
 * @version v1.0
 * @since 2023/1/18 16:42
 */
@Slf4j
@RequiredArgsConstructor
@FXMLController
public class RegisterController implements Initializable {
    @FXML
    public Pane registerPanel;
    @FXML
    public ImageView codeImg;
    @FXML
    public Button backLogin;
    @FXML
    public Button register;
    @FXML
    public Button reset;
    @FXML
    public TextField account;
    @FXML
    public TextField code;
    @FXML
    public PasswordField password;
    @FXML
    public PasswordField passwordSure;
    @FXML
    public Label accountTip;
    @FXML
    public Label passwordTip;
    @FXML
    public Label passwordSureTip;
    @FXML
    public Label codeTip;

    private String checkCode;

    private final LoginService loginService;

    @Resource
    private TransactionTemplate transactionTemplate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setCodeImage();
    }

    private void setCodeImage() {
        checkCode = RandomUtil.randomString(SourceEnum.numLetter.getSources(), 4);
        File tempFile;
        Image image;
        try {
            tempFile = Files.createTempFile("tempImg", ".png").toFile();
            ImageUtil.createImage(checkCode, tempFile);
            image = new Image(Files.newInputStream(tempFile.toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        codeImg.setImage(image);
    }

    @FXML
    public void register() {
        if (!this.valueCheck()) {
            return;
        }
        transactionTemplate.execute(status -> {
            try {
                loginService.register(account.getText(), password.getText());
                return null;
            } catch (Exception e) {
                log.error("注册业务异常，回滚！");
                status.setRollbackOnly();
                AlertUtil.showSystemError();
                throw new RuntimeException("系统异常！");
            }
        });
        AlertUtil.confirmAlertToPage("账号创建成功！是否前往登录？", LoginView.class);
    }

    private boolean valueCheck() {
        if (StrUtil.isBlank(account.getText())) {
            accountTip.setText("请输入账号！");
            return false;
        }

        if (StrUtil.isBlank(password.getText())) {
            passwordTip.setText("请输入密码！");
            return false;
        }

        if (StrUtil.isBlank(passwordSure.getText())) {
            passwordSureTip.setText("请再次输入密码！");
            return false;
        }

        if (StrUtil.isBlank(code.getText())) {
            codeTip.setText("请输入验证码！");
            return false;
        }

        if (!code.getText().equalsIgnoreCase(checkCode)) {
            codeTip.setText("验证码有误！");
            return false;
        }

        if (!password.getText().equals(passwordSure.getText())) {
            passwordSureTip.setText("密码不相同，请重新输入！");
            return false;
        }

        long count = loginService.countByAccount(account.getText());
        if (count > 0) {
            AlertUtil.showError("账号已存在！");
            return false;
        }
        return true;
    }

    @FXML
    protected void reset() {
        account.setText("");
        accountTip.setText("");
        password.setText("");
        passwordSure.setText("");
        code.setText("");
        passwordTip.setText("");
        codeTip.setText("");
        passwordSureTip.setText("");
    }

    @FXML
    protected void backLogin() {
        BmsApplication.showView(LoginView.class);
    }

    @FXML
    protected void accountClickListener() {
        accountTip.setText("");
    }

    @FXML
    protected void passwordClickListener() {
        passwordTip.setText("");
    }

    @FXML
    protected void passwordSureClickListener() {
        passwordSureTip.setText("");
    }

    @FXML
    protected void codeClickListener() {
        codeTip.setText("");
    }

    @FXML
    protected void imageClickListener() {
        this.setCodeImage();
    }
}
