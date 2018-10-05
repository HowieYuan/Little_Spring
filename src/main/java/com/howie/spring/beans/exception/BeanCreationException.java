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
    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String message) {
        super(message);
    }
}
