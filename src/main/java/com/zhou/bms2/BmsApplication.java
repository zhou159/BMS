package com.zhou.bms2;

import com.zhou.bms2.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * @author zhouxiong
 * @since 2022/6/9 9:53
 */
@MapperScan("com.zhou.bms2.mapper")
@SpringBootApplication
public class BmsApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(BmsApplication.class, LoginView.class, args);
    }

    @Override
    public Collection<Image> loadDefaultIcons() {
        return Collections.singletonList(new Image(Objects.requireNonNull(this.getClass().getResource("/static/write.png")).toExternalForm()));
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image("/static/write.png"));
        stage.setTitle("BMS图书管理系统+v2.0");
        //不可拉伸
        stage.setResizable(false);
        super.start(stage);
    }
}
