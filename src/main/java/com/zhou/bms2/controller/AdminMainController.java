package com.zhou.bms2.controller;

import cn.hutool.cache.Cache;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.system.UserInfo;
import com.zhou.bms2.view.BookManageView;
import com.zhou.bms2.view.ReaderManageView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author zhouxiong
 * @version v1.0
 * 2025/1/13 17:02
 */
@RequiredArgsConstructor
@FXMLController
public class AdminMainController implements Initializable {

    @Resource(name = "userCache")
    private Cache<String, UserInfo> cache;
    @FXML
    public Label tipNameLabel;
    @FXML
    public Button userManager;
    @FXML
    public Button bookManger;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserInfo userInfo = cache.get("userInfo");
        tipNameLabel.setText(userInfo.getName());
    }

    @FXML
    public void toUserPage() {
        BmsApplication.showView(ReaderManageView.class);
    }

    @FXML
    public void toBookPage() {
        BmsApplication.showView(BookManageView.class);
    }
}
