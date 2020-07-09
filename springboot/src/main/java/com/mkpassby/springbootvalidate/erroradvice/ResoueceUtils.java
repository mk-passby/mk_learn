package com.mkpassby.springbootvalidate.erroradvice;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @program: springcloud-demo
 * @description:
 * @author: mk_passby
 * @create: 2020-05-31 23:01
 **/
public class ResoueceUtils {
    public static String getEnglishValueByKey(String key){

        Locale locale = new Locale("en", "US");
        //使用指定的英文Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/myError", locale);
        return mySource.getString(key);
    }

    public static String getChineseValueByKey(String key){
        //中文Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/myError", Locale.CHINESE);
        return mySource.getString(key);
    }

    public static String getDeafultValueByKey(String key){
        //默认Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/myError");
        return mySource.getString(key);
    }

    public static String getValueAndPlaceholder(String key){
        //默认Locale
        ResourceBundle mySource = ResourceBundle.getBundle("i18n/myError");
        String beforeValue = mySource.getString(key);
        //填充国家化文件中的占位符
        String afterValue = MessageFormat.format(beforeValue, "安全");
        return afterValue;
    }

}
