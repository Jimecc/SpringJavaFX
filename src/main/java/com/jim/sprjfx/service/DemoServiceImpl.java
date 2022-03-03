package com.jim.sprjfx.service;

import com.jim.sprjfx.SprJfxApplication;
import com.jim.sprjfx.view.MainView;
import de.felixroske.jfxsupport.AbstractFxmlView;
import de.felixroske.jfxsupport.GUIState;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import org.springframework.stereotype.Service;


@Service
public class DemoServiceImpl implements DemoService{

    @Override
    public void onMouseMoved_bg(Button button, String color) {
        Background bg = new Background(new BackgroundFill(Paint.valueOf(color), new CornerRadii(5), new Insets(1)));
        button.setBackground(bg);
    }

    @Override
    public void jumpWindows(int width, int height, Class<? extends AbstractFxmlView> newView) {
        GUIState.getStage().setWidth(width);
        GUIState.getStage().setHeight(height);
//            GUIState.getStage().set
        // 将舞台置于窗口中央
        GUIState.getStage().centerOnScreen();
        SprJfxApplication.showView(newView);
    }

    @Override
    public void moveCircle(int fromX, int endX, int fromY, int endY, int time, Circle circle) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(time * 100), circle);
        translateTransition.setFromX(fromX);
        translateTransition.setToX(endX);
        translateTransition.setFromY(fromY);
        translateTransition.setToY(endY);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);
        translateTransition.play();//启动动画
    }

    @Override
    public void toBig(Circle circle) {
        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(1500), circle);
        scaleTransition.setToX(2f);
        scaleTransition.setToY(2f);
        scaleTransition.setCycleCount(2);
        scaleTransition.setAutoReverse(true);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(
                scaleTransition
        );
        parallelTransition.setCycleCount(Timeline.INDEFINITE);
        parallelTransition.play();
    }


}
