package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.factory.config.SingletonBeanRegistry;
import com.howie.spring.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 19:58
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 获得单例bean
     */
    @Override
    public Object getSingletonBean(String beanName) {
        return this.singletonObjects.get(beanName);
    }

    /**
     * 注册单例bean
     */
    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, beanName + " can not be null!");
        Object oldObject = this.singletonObjects.get(beanName);
        //如果该bean已存在，则不符合单例，报错
        if (oldObject != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanName + "': there is already object [" +
                    oldObject + "] bound");
        }
        this.singletonObjects.put(beanName, singletonObject);
    }
}
