package com.zhou.bms2.controller;

import cn.hutool.cache.Cache;
import cn.hutool.core.util.StrUtil;
import com.zhou.bms2.BmsApplication;
import com.zhou.bms2.entity.Reader;
import com.zhou.bms2.service.ReaderService;
import com.zhou.bms2.system.UserInfo;
import com.zhou.bms2.system.util.AlertUtil;
import com.zhou.bms2.view.LoginView;
import com.zhou.bms2.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.RequiredArgsConstructor;

import javax.annotation.Resource;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @author zhouxiong
 * @version v1.0
 * 2025/1/13 17:02
 */
@RequiredArgsConstructor
@FXMLController
public class ReaderInitController implements Initializable {

    @FXML
    public TextField name;
    @FXML
    public TextField age;
    @FXML
    public ComboBox<String> profession;
    @FXML
    public ComboBox<String> sex;

    private UserInfo userInfo;

    @Resource(name = "userCache")
    private Cache<String, UserInfo> cache;

    private final ReaderService readerService;

    private final static Map<String, Integer> PROFESSION_MAP = new HashMap<>();
    private final static Map<String, Integer> SEX_MAP = new HashMap<>();

    static {
        PROFESSION_MAP.put("学生", 0);
        PROFESSION_MAP.put("教职工", 1);

        SEX_MAP.put("男", 1);
        SEX_MAP.put("女", 2);
        SEX_MAP.put("未说明", 9);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> options = FXCollections.observableArrayList("学生", "教职工");
        profession.setItems(options);
        profession.setPromptText("请选择职业");
        profession.setEditable(false);

        ObservableList<String> options2 = FXCollections.observableArrayList("男", "女", "未说明");
        sex.setItems(options2);
        sex.setPromptText("请选择性别");
        sex.setEditable(false);

        age.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int value = Integer.parseInt(newValue);
                if (value > 100 || value <= 0) {
                    age.setText(oldValue); // 如果输入值大于最大值，则恢复旧值
                }
            } catch (NumberFormatException e) {
                // 如果输入的不是数字，则禁止输入
                age.clear();
            }
        });

        userInfo = cache.get("userInfo");
    }

    @FXML
    public void confirm() {
        if (!this.checkValue()) {
            return;
        }

        // 职业
        int professionValue = PROFESSION_MAP.get(profession.getSelectionModel().getSelectedItem());
        // 性别
        int sexValue = 0;
        if (sex.getSelectionModel().getSelectedItem() != null) {
            sexValue = SEX_MAP.get(sex.getSelectionModel().getSelectedItem());
        }

        boolean update = readerService.lambdaUpdate()
                .eq(Reader::getId, userInfo.getUserId())
                .set(Reader::getName, name.getText())
                .set(Reader::getStatus, 1)
                .set(StrUtil.isNotBlank(age.getText()), Reader::getAge, Integer.parseInt(age.getText()))
                .set(sex.getSelectionModel().getSelectedItem() != null, Reader::getSex, sexValue)
                .set(Reader::getProfession, professionValue)
                .update();

        if (update) {
            AlertUtil.confirmAlertToPage("修改成功！是否前往主页？", MainView.class);
        }

        userInfo.setName(name.getText());
        userInfo.setStatus("1");
    }

    private boolean checkValue() {
        if (StrUtil.isBlank(name.getText())) {
            AlertUtil.showError("请输入名称！");
            return false;
        }

        if (profession.getSelectionModel().getSelectedItem() == null) {
            AlertUtil.showError("请选择职业！");
            return false;
        }
        return true;
    }

    @FXML
    public void reset() {
        name.setText("");
        age.clear();
        profession.getSelectionModel().clearSelection();
        sex.getSelectionModel().clearSelection();
    }

    @FXML
    public void backLogin() {
        BmsApplication.showView(LoginView.class);
    }
}
