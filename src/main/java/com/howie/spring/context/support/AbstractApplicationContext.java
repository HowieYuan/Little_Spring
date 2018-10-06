package com.howie.spring.context.support;

import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.context.ApplicationContext;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.Resource;
import com.howie.spring.util.ClassUtils;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 模版方法设计模式，将不同类型的 Resource 固定在一个模版方法中
 * @Date 2018-10-06
 * @Time 14:41
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    private DefaultBeanFactory beanFactory = null;
    private ClassLoader classLoader = null;

    public AbstractApplicationContext(String configFile) {
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        //getResource 获得不同类型的 Resource
        Resource resource = this.getResource(configFile);
        reader.loadBeanDefinition(resource);
        beanFactory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public Object getBean(String beanID) {
        return beanFactory.getBean(beanID);
    }

    abstract public Resource getResource(String configFile);

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }
}
