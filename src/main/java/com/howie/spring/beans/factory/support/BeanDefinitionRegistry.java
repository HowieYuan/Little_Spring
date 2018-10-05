package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.BeanDefinition;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description BeanDefinition的注册接口（获取与写入）
 * @Date 2018-10-05
 * @Time 22:34
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanID);

    void registryBeanDefinition(String id, BeanDefinition bd);
}
