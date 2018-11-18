package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.factory.BeanFactory;
import com.howie.spring.beans.factory.config.RuntimeBeanReference;
import com.howie.spring.beans.factory.config.TypedStringValue;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-11-17
 * @Time 21:56
 */
public class BeanDefinitionValueResolver {
    private BeanFactory beanFactory;

    public BeanDefinitionValueResolver(DefaultBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessary(Object value) {
        //如果属于RuntimeBeanReference，即ref，则返回实例值
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String beanName = reference.getBeanName();
            return beanFactory.getBean(beanName);
            //如果属于TypedStringValue，即value，则返回原值
        } else if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        } else {
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
