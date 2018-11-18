package com.howie.springTest.test;

import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.FileSystemResource;
import com.howie.spring.core.io.Resource;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 1:41
 */
public class ResourceTest {
    @Test
    public void ClassPathResourceTest() throws Exception {
        Resource resource = new ClassPathResource("bean.xml");
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
        Resource resource = new FileSystemResource("src\\test\\resources\\bean.xml");
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
