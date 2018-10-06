package com.howie.spring.beans.factory.config;

import com.howie.spring.beans.factory.BeanFactory;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 15:54
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
