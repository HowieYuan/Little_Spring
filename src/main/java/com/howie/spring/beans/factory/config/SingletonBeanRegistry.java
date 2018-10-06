package com.howie.spring.beans.factory.config;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 单例bean注册
 * @Date 2018-10-06
 * @Time 19:56
 */
public interface SingletonBeanRegistry {
    Object getSingletonBean(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
