package com.howie.spring.beans;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 核心，封装了一个bean的所有定义
 * @Date 2018-10-04
 * @Time 17:33
 */
public interface BeanDefinition {
    //scope = singleton
    String SCOPE_SINGLETON = "singleton";
    //scope = prototype
    String SCOPE_PROTOTYPE = "prototype";
    //scope 默认为空
    String SCOPE_DEFAULT = "";

    boolean isSingleton();
    boolean isPrototype();
    String getScope();

    /**
     * 设置 scope 属性
     */
    void setScope(String scope);

    String getBeanClassName();

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getID();

    boolean hasConstructorArgumentValues();
}
