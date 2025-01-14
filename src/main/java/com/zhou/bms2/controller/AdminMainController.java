package com.zhou.bms2.controller;

import com.zhou.bms2.system.UserInfo;
import de.felixroske.jfxsupport.FXMLController;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;

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
    
    private final UserInfo userInfo;
    public Label tipNameLabel;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tipNameLabel.setText(userInfo.getName());
    }
}
