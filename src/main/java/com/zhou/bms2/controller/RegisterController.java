package com.zhou.bms2.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.entity.Login;
import com.zhou.bms2.entity.Reader;
import com.zhou.bms2.entity.ReaderRole;
import com.zhou.bms2.service.LoginService;
import com.zhou.bms2.service.ReaderRoleService;
import com.zhou.bms2.service.ReaderService;
import com.zhou.bms2.system.enums.SourceEnum;
import com.zhou.bms2.util.ImageUtil;
import com.zhou.bms2.view.LoginView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @author zhouxiong
 * @version v1.0
 * @since 2023/1/18 16:42
 */
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
    private final ReaderService readerService;
    private final ReaderRoleService readerRoleService;

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
        this.buildLogin(account.getText(), password.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "账号创建成功！是否前往登录？",
                new ButtonType("取消", ButtonBar.ButtonData.NO),
                new ButtonType("确定", ButtonBar.ButtonData.YES));
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent()) {
            if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                BmsApplication.showView(LoginView.class);
            }
        }
    }

    private void buildLogin(String account, String password) {
        Reader reader = this.buildReader();

        Login login = new Login();
        login.setAccount(account);
        login.setPassword(password);
        login.setReaderId(reader.getId());
        loginService.save(login);
    }

    private Reader buildReader() {
        Reader reader = new Reader();
        readerService.save(reader);
        this.buildReaderRole(reader.getId());
        return reader;
    }

    private void buildReaderRole(String readerId) {
        ReaderRole readerRole = new ReaderRole();
        readerRole.setReaderId(readerId);
        readerRole.setRoleId("2");
        readerRoleService.save(readerRole);
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
        codeImg.setImage(null);
        this.setCodeImage();
    }
}
