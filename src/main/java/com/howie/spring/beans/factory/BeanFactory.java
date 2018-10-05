package com.howie.spring.beans.factory;

import com.howie.spring.beans.BeanDefinition;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description bean工厂（获取bean）
 * @Date 2018-10-04
 * @Time 17:30
 */
public interface BeanFactory {
    Object getBean(String beanID);
}
