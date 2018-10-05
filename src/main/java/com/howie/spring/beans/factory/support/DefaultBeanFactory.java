package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.exception.BeanCreationException;
import com.howie.spring.util.ClassUtils;
import com.howie.spring.beans.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 处理bean和BeanDefinition的工厂
 * @Date 2018-10-04
 * @Time 17:30
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {
    /**
     * xml 文件中各个 <bean>
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);

    /**
     * 获得某个 bean 的 BeanDefinition
     *
     * @param beanID bean id
     */
    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public void registryBeanDefinition(String id, BeanDefinition bd) {
        this.beanDefinitionMap.put(id, bd);
    }

    /**
     * 获得某个 bean 对象
     *
     * @param beanID bean id
     */
    @Override
    public Object getBean(String beanID) {
        //GenericBeanDefinition
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if (bd == null) {
            throw new BeanCreationException("Bean Definition does not exist");
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }
}
