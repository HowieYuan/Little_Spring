package com.howie.spring.test;

import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.FileSystemResource;
import com.howie.spring.core.io.Resourse;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 1:41
 */
public class ResourseTest {
    @Test
    public void ClassPathResourceTest() throws Exception {
        Resourse resource = new ClassPathResource("bean.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void FileSystemResourceTest() throws Exception {
        Resourse resource = new FileSystemResource("D:\\学习\\myproject\\MySpring\\src\\main\\resources\\bean.xml");
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            Assert.assertNotNull(inputStream);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    @Test
    public void FileSystemResourceNullTest() throws Exception {
        try {
            new FileSystemResource(null);
        } catch (IllegalArgumentException ignored) {
            return;
        }
        Assert.fail("未抛出IllegalArgumentException");
    }
}
