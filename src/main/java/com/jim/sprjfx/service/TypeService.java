package com.jim.sprjfx.service;

public interface TypeService {

    public String returnType(String str);   // 返回中文
    // 纯中文
    public boolean ifPureChinese(String str);
    // 纯数字
    public boolean ifPureNumber(String str);
    // 纯 String
    public boolean ifPureString(String str);
    // 纯字符  ！@#￥%……&*（
    public boolean ifPureChar(String str);
    // 不纯
    public boolean ifNotPure(String str);
}
