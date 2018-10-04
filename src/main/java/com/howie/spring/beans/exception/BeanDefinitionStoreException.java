package com.howie.spring.beans.exception;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 读取 xml 文件出错异常
 * @Date 2018-10-04
 * @Time 20:36
 */
public class BeanDefinitionStoreException extends RuntimeException {
    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
