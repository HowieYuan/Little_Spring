package com.howie.spring.beans.factory.config;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description ref类型property
 * @Date 2018-11-15
 * @Time 14:17
 */
public class RuntimeBeanReference {
    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
