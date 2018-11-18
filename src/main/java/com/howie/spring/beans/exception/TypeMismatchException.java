package com.howie.spring.beans.exception;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-11-18
 * @Time 17:32
 */
public class TypeMismatchException extends BeanException {
    private transient Object value;

    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value :" + value + "to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
