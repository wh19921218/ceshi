package com.cn.lianshou.common.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Locale;

/**
 * FileName: com.cn.lianshou.common.util.SpringContextUtil.java
 * Author: Wanghh
 * Date: 2018/4/12 9:21
 */
public class SpringContextUtil implements ApplicationContextAware{

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext contex)
            throws BeansException {

        this.context = contex;
    }
    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static String getMessage(String key){
        return context.getMessage(key, null, Locale.getDefault());
    }
}
