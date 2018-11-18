package com.howie.spring.beans;

import com.sun.corba.se.impl.io.TypeMismatchException;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 类型转换器
 * @Date 2018-11-18
 * @Time 17:17
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
