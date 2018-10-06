package com.howie.spring.test;

import com.howie.spring.context.ApplicationContext;
import com.howie.spring.context.support.ClassPathXMLApplicationContext;
import com.howie.spring.context.support.FileSystemXMLApplicationContext;
import com.howie.spring.sevice.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 0:58
 */
public class ApplicationContextTest {
    @Test
    public void testClassPathGetBean() {
        ApplicationContext context = new ClassPathXMLApplicationContext("bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testFileSystemGetBean() {
        ApplicationContext context = new FileSystemXMLApplicationContext("D:\\学习\\myproject\\MySpring\\src\\main\\resources\\bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
