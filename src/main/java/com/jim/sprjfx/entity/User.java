package com.jim.sprjfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  编码：hsk
 *  时间：2022年2月10日09:46:15
 * */
// get set 方法
@Data
// 有参构造
@AllArgsConstructor
// 无参构造
@NoArgsConstructor
public class User {
    private String nickname;    //  昵称
    private String username;    //  用户名
    private String password;    //  密码
    private String salt;        //  盐
    private String creator;     //  创建者


}
