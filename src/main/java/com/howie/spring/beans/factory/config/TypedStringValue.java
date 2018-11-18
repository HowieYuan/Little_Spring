package com.howie.spring.beans.factory.config;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description value类型property
 * @Date 2018-11-15
 * @Time 14:24
 */
public class TypedStringValue {
    private final String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
