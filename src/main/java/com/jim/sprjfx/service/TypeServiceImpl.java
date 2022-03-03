package com.jim.sprjfx.service;

import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService{
    /**
     * - numOf_CH(String str)       纯字符 ！@#￥%……&*（）——+
     * - ifPureChinese(String str)  纯中文 你好世界
     * - ifPureNumber(String str)   纯数字 1234567890
     * - ifPureString(String str)   纯字串 “asdfghhgfdsa”
     * - ifNotPure(String str)      不纯  “新年快乐5676543sadfa--_)(*&^%$#”
     */

    @Override
    public String returnType(String str) {
        if(notNull(str)) return "NULL";
        if(ifPureChinese(str)) return "pureChinese";
        if(ifPureNumber(str)) return "pureNumber";
        if(ifPureString(str)) return "pureString";
        if(ifPureChar(str)) return "pureChar";
        if(ifNotPure(str)) return "notPure";

        return "unKnown";
    }

    @Override
    public boolean ifPureChinese(String str) {
         return str.length() == numOfChinese(str);
    }

    @Override
    public boolean ifPureNumber(String str) {
        return str.length() == numOfNum(str);
    }

    @Override
    public boolean ifPureString(String str) {
        return str.length() == numOfChar(str);
    }

    @Override
    public boolean ifPureChar(String str) {
        return str.length() == numOf_CH(str);
    }

    @Override
    public boolean ifNotPure(String str) {
        return (!ifPureNumber(str) && !ifPureChinese(str) && !ifPureString(str));
    }


    /**
     * not Null
     */
    public boolean notNull(String str) {
        return (str.equals(""));
    }
    /**
     * pure Chinese
     * @param c
     * @return
     */
    public boolean isChinese(char c) {
        return c >= 0x4E00 &&  c <= 0x9FA5;
    }

    public int numOfChinese(String str) {
        int i = 0;
        if (str == null) return i;
        for (char c : str.toCharArray()) {
            if (isChinese(c))  i=i+1;
        }
        return i;
    }

    /**
     * pure String
     * @param str
     * @return
     */
    public int numOfChar(String str) {
        int i = 0;
        if (notNull(str)) return i;
        for (char c : str.toCharArray()) {
            if ((c>='a' && c<='z')||c>='A'&&c<='Z')  i=i+1;
        }
        return i;
    }
    /**
     * pure Numbers
     * @param str
     * @return
     */
    public int numOfNum(String str) {
        int i = 0;
        if (notNull(str)) return i;
        for (char c : str.toCharArray()) {
            if (c>='0' && c<='9')  i=i+1;
        }
        return i;
    }

    /**
     * 纯字符
     * @param str
     * @return
     */
    public int numOf_CH(String str){
        int i = 0;
        if (notNull(str)) return i;
        for (char c : str.toCharArray()) {
            if ((c>='!' && c<='/')||(c>=':'&&c<='@')||(c>='['&&c<='`')||(c>='{'&&c<='~'))  i=i+1;
        }
        return i;
    }

}
