package com.howie.spring.beans;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-11-15
 * @Time 14:06
 */
public class PropertyValue {
    private final String name;
    private final Object value;

    private boolean converted = false;

    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
