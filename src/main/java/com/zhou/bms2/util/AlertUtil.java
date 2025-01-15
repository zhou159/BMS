package com.zhou.bms2.util;

import com.zhou.bms2.BmsApplication;
import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * @author zhouxiong
 * @version v1.0
 * 2025/1/15 15:16
 */
public class AlertUtil {
    /**
     * 系统通用异常提示
     */
    public static void showSystemError() {
        showError("系统异常！");
    }

    /**
     * 通用异常提示
     *
     * @param contentText 异常消息
     */
    public static void showError(String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(contentText);
        alert.show();
    }

    /**
     * 通用对话框，只有两个按钮，确定和取消
     *
     * @param contentText 提示框内容
     * @param confirmText 确认按钮文本
     * @param cancelText  取消按钮文本
     * @param runnable    确认按钮执行逻辑
     */
    public static void confirmAlert(String contentText, String confirmText, String cancelText, Runnable runnable) {
        Alert alert = buildConfirmAlert(contentText, confirmText, cancelText);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent()) {
            if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                runnable.run();
            }
        }
    }

    /**
     * 通用对话框，只有两个按钮，确定和取消，确定按钮事件会跳转至另一个页面
     *
     * @param contentText 提示框内容
     * @param confirmText 确认按钮文本
     * @param cancelText  取消按钮文本
     * @param targetClass 页面
     */
    public static void confirmAlertToPage(String contentText, String confirmText, String cancelText, Class<? extends AbstractFxmlView> targetClass) {
        Alert alert = buildConfirmAlert(contentText, confirmText, cancelText);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent()) {
            if (buttonType.get().getButtonData().equals(ButtonBar.ButtonData.YES)) {
                BmsApplication.showView(targetClass);
            }
        }
    }

    public static void confirmAlertToPage(String contentText, Class<? extends AbstractFxmlView> targetClass) {
        confirmAlertToPage(contentText, "确定", "取消", targetClass);
    }

    /**
     * 构建一个对话提示框
     *
     * @param contentText 提示框内容
     * @param confirmText 确认按钮文本
     * @param cancelText  取消按钮文本
     * @return 对话提示框
     */
    private static Alert buildConfirmAlert(String contentText, String confirmText, String cancelText) {
        return new Alert(Alert.AlertType.CONFIRMATION,
                contentText,
                new ButtonType(cancelText, ButtonBar.ButtonData.NO),
                new ButtonType(confirmText, ButtonBar.ButtonData.YES));
    }
}
