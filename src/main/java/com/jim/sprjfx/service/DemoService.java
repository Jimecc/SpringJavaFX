package com.jim.sprjfx.service;

import de.felixroske.jfxsupport.AbstractFxmlView;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;

public interface DemoService {
    public void onMouseMoved_bg(Button button, String color);
    public void jumpWindows(int width,int height,Class<? extends AbstractFxmlView> newView);
    public void moveCircle(int fromX, int endX, int fromY, int endY, int time, Circle circle);
    public void toBig(Circle circle);
}
