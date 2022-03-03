package com.jim.sprjfx.controller;

import com.jim.sprjfx.SprJfxApplication;
import com.jim.sprjfx.entity.User;
import com.jim.sprjfx.service.PasswordUtil;
import com.jim.sprjfx.service.UserService;
import com.jim.sprjfx.view.MainView;
import de.felixroske.jfxsupport.FXMLController;
import de.felixroske.jfxsupport.GUIState;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.util.Password;

@FXMLController
public class RegistController {

    public TextField TextFiled_nickname;
    public TextField TextFiled_username;
    public TextField TextFiled_password;
    public TextField TextFiled_repassword;
    public Button button_submit;
    public Button button_loginNow;
    public Label label1;
    public Label label2;
    public Label label3;
    public Label label4;
    public Label label5;

    @Autowired
    public UserService userService ;


    public void onMouseMo_button_submit(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#00BFFF"), new CornerRadii(3),new Insets(2)));
        button_submit.setBackground(bg);
    }
    public void onMouseMo_button_loginNow(){
        Background bg = new Background(new BackgroundFill(Paint.valueOf("#F5F5F5"), new CornerRadii(3),new Insets(2)));
        button_loginNow.setBackground(bg);
    }

    public void onMouseClicked_button_LoginNow(){
        GUIState.getStage().setWidth(520);
        GUIState.getStage().setHeight(380);
//            GUIState.getStage().set
        // 将舞台置于窗口中央
        GUIState.getStage().centerOnScreen();
        SprJfxApplication.showView(MainView.class);
    }

    /**
     *  提交注册
     */
    public void onMouseClicked_button_submir(){
        User user = new User();
        PasswordUtil passwordUtil = new PasswordUtil();

        String nickname = TextFiled_nickname.getText();
        String username = TextFiled_username.getText();

        String password = "";
        if (TextFiled_password.getText()!=null) {
            password = TextFiled_password.getText();
        }

        String repassword = TextFiled_repassword.getText();
        if("".equals(nickname)){
            errorInfoTextField(TextFiled_nickname);
            label1.setText("昵称不可为空");
        }
        if("".equals(username)){
            errorInfoTextField(TextFiled_username);
            label2.setText("用户名不可为空");
        }
        if(isChinese(username)){
            errorInfoTextField(TextFiled_username);
            label2.setText("用户名不可以包含中文");
        }
        if("".equals(password)){
            errorInfoTextField(TextFiled_password);
            label3.setText("请输入密码");
        }
        if("".equals(repassword)){
            errorInfoTextField(TextFiled_repassword);
            label4.setText("请再次输入密码");
            return;
        }
        if(!password.equals(repassword)){
            errorInfoTextField(TextFiled_password);
            label3.setText("两次密码不一致");
            errorInfoTextField(TextFiled_repassword);
            label4.setText("两次密码不一致");
            return;
        }
        String salt = passwordUtil.randomGen(8);
        user.setNickname(nickname);
        user.setUsername(username);
        user.setSalt(salt);
        user.setPassword(passwordUtil.encrypt(username,password,salt));
        try {
            // 尝试写入数据库
            userService.newUser(user);
            System.out.println("true");
            GUIState.getStage().setWidth(520);
            GUIState.getStage().setHeight(380);
//            GUIState.getStage().set
            // 将舞台置于窗口中央
            GUIState.getStage().centerOnScreen();
            SprJfxApplication.showView(MainView.class);
        }catch (Exception e){
            // 写入数据库失败
            System.out.println("false");
            errorInfoTextField(TextFiled_username);
            label5.setText("该账户已经被注册，请重新输入。");
        }
    }

    public void onMouseClicked_clear(){
        TextFiled_username.setText(null);
    }

    /**
     * 功能：检查是否重新输入
     * 开发：hsk
     * 时间：2022年2月11日15:13:52
     * 函数：4+1
     */

    public void onMouseClicked1(){onMouseClicked();}
    public void onMouseClicked2(){onMouseClicked();}
    public void onMouseClicked3(){onMouseClicked();}
    public void onMouseClicked4(){onMouseClicked();}
    public void onMouseClicked(){
        initInfoTextField(TextFiled_nickname,label1);
        initInfoTextField(TextFiled_username,label2);
        initInfoTextField(TextFiled_password,label3);
        initInfoTextField(TextFiled_repassword,label4);
        label5.setText(null);
    }
    /**
     * 功能：点击回车键登录
     * 开发：hsk
     * 时间：2022年2月11日15:17:37
     * 函数：4
     */
    public void onMouseEntered1(ActionEvent ae){
        onMouseClicked_button_submir();
    }
    public void onMouseEntered2(ActionEvent ae){
        onMouseClicked_button_submir();
    }
    public void onMouseEntered3(ActionEvent ae){
        onMouseClicked_button_submir();
    }
    public void onMouseEntered4(ActionEvent ae){
        onMouseClicked_button_submir();
    }
    /**
     * 功能：爆红与重置
     * 开发：hsk
     * 时间：2022年2月11日15:13:03
     * @param textField
     */
    public void errorInfoTextField(TextField textField){
        BorderStroke bos = new BorderStroke(Paint.valueOf("#FF3000"), BorderStrokeStyle.SOLID,new CornerRadii(3), new BorderWidths((1)));
        Border bo = new Border(bos);
        textField.setBorder(bo);
    }
    public void initInfoTextField(TextField textField,Label label){
        textField.setBorder(null);
        label.setText(null);
    }


    /**
     * 功能：判断是否含有中文 或 特殊符号
     * 开发：hsk
     * 时间：2022年2月11日14:21:11
     */


// 判断一个字符是否是中文
    public static boolean isChinese(char c) {
        return c >= 0x4E00 &&  c <= 0x9FA5;// 根据字节码判断
    }
    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null) return false;
        for (char c : str.toCharArray()) {
            if (isChinese(c)) return true;// 有一个中文字符就返回
        }
        return false;
    }

}
