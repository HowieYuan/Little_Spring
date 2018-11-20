package com.howie.spring.beans.exception;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 创建 bean 出错异常
 * @Date 2018-10-04
 * @Time 20:35
 */
public class BeanCreationException extends BeanException {
    private String beanName;
    public BeanCreationException(String msg) {
        super(msg);

    }
    public BeanCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }
    public String getBeanName(){
        return this.beanName;
    }
}
