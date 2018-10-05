package com.howie.spring.context.support;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.factory.BeanFactory;
import com.howie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.context.ApplicationContext;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 0:59
 */
public class ClassPathApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory = null;

    public ClassPathApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(configFile);
    }

    @Override
    public Object getBean(String beanID) {
        return beanFactory.getBean(beanID);
    }
}
