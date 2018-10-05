package com.howie.spring.core.io;

import com.howie.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 1:34
 */
public class ClassPathResource implements Resourse {
    private String classPath;
    private ClassLoader classLoader;

    public ClassPathResource(String classPath) {
        this(classPath, null);
    }

    public ClassPathResource(String classPath, ClassLoader classLoader) {
        this.classPath = classPath;
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream =  classLoader.getResourceAsStream(classPath);
        if (inputStream == null) {
            throw new FileNotFoundException(classPath + "can not open!");
        }
        return inputStream;
    }

    @Override
    public String getDescription() {
        return classPath;
    }
}
