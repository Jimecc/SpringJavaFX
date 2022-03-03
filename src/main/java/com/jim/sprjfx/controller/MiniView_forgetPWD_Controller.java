package com.jim.sprjfx.controller;

import com.jim.sprjfx.SprJfxApplication;
import com.jim.sprjfx.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;

@FXMLController
public class MiniView_forgetPWD_Controller {
    public void onMouseClicked_ok(){
        GUIState.getStage().setWidth(520);
        GUIState.getStage().setHeight(380);
//            GUIState.getStage().set
        // 将舞台置于窗口中央
        GUIState.getStage().centerOnScreen();
        SprJfxApplication.showView(MainView.class);
    }
}
