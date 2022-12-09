package com.zhou.bms2;

import com.zhou.bms2.view.LoginView;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zhou.bms2.mapper")
@SpringBootApplication
public class BmsApplication extends AbstractJavaFxApplicationSupport {
    public static void main(String[] args) {
        launch(BmsApplication.class, LoginView.class, args);
    }
}