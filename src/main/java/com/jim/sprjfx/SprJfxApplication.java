package com.jim.sprjfx;

import com.jim.sprjfx.service.AsyncTaskService;
import com.jim.sprjfx.view.*;
import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class SprJfxApplication extends AbstractJavaFxApplicationSupport {


    public static void main(String[] args) {
        // 启动并设置启动动画不可见
        launch(SprJfxApplication.class, DemoView.class,new SplashScreen(){
            public boolean visible() { return false; }
        },args);
    }
//    public static void main(String[] args) {
//        // 启动并设置启动动画不可见
//
//        launch(SprJfxApplication.class, MainView.class,new SplashScreen(){
//            public boolean visible() { return false; }
//        },args);
//
//    }

    @Override
    public void beforeInitialView(Stage stage, ConfigurableApplicationContext ctx) {
        stage.setResizable(false); /* 设置窗口不可改变 */
//      窗口置顶
//      stage.setAlwaysOnTop(true);
//      stage.initStyle(StageStyle.TRANSPARENT); /* 透明标题栏 */
    }

}
