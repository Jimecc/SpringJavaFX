package com.jim.sprjfx.controller;

import com.jim.sprjfx.SprJfxApplication;
import com.jim.sprjfx.entity.User;
import com.jim.sprjfx.service.PasswordUtil;
import com.jim.sprjfx.service.UserService;
import com.jim.sprjfx.view.DemoView;
import com.jim.sprjfx.view.MainView;
import com.jim.sprjfx.view.MiniView_cantRegist;
import com.jim.sprjfx.view.MiniView_forgetPWD;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import de.felixroske.jfxsupport.SplashScreen;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;


@FXMLController
public class MainController implements Initializable {

    @Autowired
    private DemoView demoView;
    @Autowired
    private UserService userService;



    // 与 fxml 文件中的ButtonId进行绑定
    public Label labelLogin;
    public Button buttonLogin;
    public Button buttonForget;
    public Button buttonRegister;
    public CheckBox chexkBox_showPossword;
    public TextField textField_username;
    public TextField textField_password;
    public AnchorPane window_login;
    public Button button_clear1;
    public Button button_clear2;

    private String errorInfo = "用户名或密码错误，请核对后重试";

    @Override
    public void initialize(URL locations, ResourceBundle resources){
        Stage stage = GUIState.getStage();
        stage.setTitle("上海世沃新承");
//        stage.getIcons().add(new Image("/img/sunward.png"));
        textField_username.setTooltip(new Tooltip("请输入用户名"));
        textField_password.setTooltip(new Tooltip("请输入密码"));
        buttonRegister.setTooltip(new Tooltip("抱歉，由于管理员设置，您无法自行注册，如需注册请联系管理员。"));
    }


    // 输入时初始化设置
    public void onMouseClicked_username(){initInfoTextField();}
    public void onMouseClicked_password(){initInfoTextField();}

    // 在用户名/密码中点击回车键登录
    public void onEnter_username(ActionEvent ae){
        tryLogin();
    }
    public void onEnter_password(ActionEvent ae){
        tryLogin();
    }

    public void show_Password(){
        System.out.println(chexkBox_showPossword.getClip());
    }


    // 点击【登录】按钮
    public void tryLogin(){
        PasswordUtil passwordUtil = new PasswordUtil();

        /**
         * 开发：hsk
         * 时间：2022年2月11日11:25:13
         * */
        // 前端输入的账户与密码
        String username = textField_username.getText();
        String password = textField_password.getText();
        String db_username = "";
        String db_password = "";
        String db_salt = "";

        /**
         * 0
         * 0
         * FZ4mrVxi
         * */
        try {
            // 数据库的账户、密码、盐
            User user = userService.getUserByUserName(username);
            db_username = user.getUsername();
            db_password = user.getPassword();
            db_salt = user.getSalt();
        } catch(Exception e){
            // 密码错误 方框变红
            errorInfoTextField();
            return;
        }



        String pwd = passwordUtil.encrypt(username, password, db_salt);

        System.out.println(pwd);
        if(pwd.equals(db_password)) {
            GUIState.getStage().setWidth(650);
            GUIState.getStage().setHeight(500);
//            GUIState.getStage().set
            // 将舞台置于窗口中央
            GUIState.getStage().centerOnScreen();
            SprJfxApplication.showView(DemoView.class);
        }else{
            // 密码错误 方框变红
            errorInfoTextField();
        }
    }

    /**
     * 功能：注册&忘记密码
     * default 不可用
     * 开发：hsk
     * 时间：2022年2月11日17:08:00
     */
    public void onMouseClicked_buttonRegister(){
        GUIState.getStage().setWidth(340);
        GUIState.getStage().setHeight(230);
        GUIState.getStage().centerOnScreen();
        SprJfxApplication.showView(MiniView_cantRegist.class);
    }
    public void onMouseClicked_buttonForget(){
        GUIState.getStage().setWidth(340);
        GUIState.getStage().setHeight(230);
        GUIState.getStage().centerOnScreen();
        SprJfxApplication.showView(MiniView_forgetPWD.class);
    }
    /**
     * 功能：显示密码
     * 开发：hsk
     * 时间：2022年2月11日12:29:34
     */

    // 显示 “显示密码”
    public void onMouseMoved_chexkBox_showPossword(){
        chexkBox_showPossword.setText("显示密码");
    }

    // 隐藏 “显示密码”
    public void onMouseExited_chexkBox_showPossword(){
        chexkBox_showPossword.setText(null);
    }

    private void showPassword(){
    }

    private void hidePassword(int length){

    }

    /**
     * 开发：hsk
     * 时间：2022年2月11日14:00:27
     */

    public void onMouseMoved_Login(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#6495ED"), new CornerRadii(3),new Insets(2)));
        buttonLogin.setBackground(bg);
    }

    public void onMouseMoved_buttonForget(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#F5F5F5"), new CornerRadii(5),new Insets(1)));
        buttonForget.setBackground(bg);
    }

    public void onMouseMoved_buttonRegister(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#F5F5F5"), new CornerRadii(5),new Insets(1)));
        buttonRegister.setBackground(bg);
    }


    /**
     * 开发：hsk
     * 时间：2022年2月11日13:59:38
     */
    public void initInfoTextField(){
        textField_username.setBorder(null);
        textField_password.setBorder(null);
        labelLogin.setText(null);
    }

    public void errorInfoTextField(){
        BorderStroke bos = new BorderStroke(Paint.valueOf("#FF3000"), BorderStrokeStyle.SOLID,new CornerRadii(3), new BorderWidths((1)));
        Border bo = new Border(bos);
        textField_username.setBorder(bo);
        textField_password.setBorder(bo);
        labelLogin.setText(errorInfo);
    }

    /**
     * 两个清空按钮
     */
    public void onMouseMoved_button1(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#B3E5FF"), new CornerRadii(3),new Insets(1)));
        button_clear1.setBackground(bg);
        button_clear1.setText("×");
    }
    public void onMouseMoved_button2(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#B3E5FF"), new CornerRadii(3),new Insets(1)));
        button_clear2.setBackground(bg);
        button_clear2.setText("×");
    }
    public void onMouseClicked_button1(){
        textField_username.setText(null);
    }
    public void onMouseClicked_button2(){
        textField_password.setText(null);
    }



    /**
     *  时间：2022年2月8日17:18:37
     *  人员：hsk
     *
     * 登录界面待添加功能：
     *      * - 点击回车键登录 √
     *      * - 点击“显示密码”显示密码 × （ 貌似需要添加新的线程，有待思考）
     *      更新时间：2022年2月9日10:38:38 -- 回车键登录
     *      更新时间：2022年2月9日13:50:36 -- 跳转
     *
     * 后端功能：
     *      * - 连接数据库
     */


}
