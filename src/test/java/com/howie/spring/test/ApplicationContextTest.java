package com.howie.spring.test;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.context.ApplicationContext;
import com.howie.spring.context.support.ClassPathApplicationContext;
import com.howie.spring.context.support.FileSystemApplicationContext;
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
        ApplicationContext context = new ClassPathApplicationContext("bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testFileSystemGetBean() {
        ApplicationContext context = new FileSystemApplicationContext("bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
