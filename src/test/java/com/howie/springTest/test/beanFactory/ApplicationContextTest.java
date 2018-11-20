package com.howie.springTest.test.beanFactory;

import com.howie.spring.context.ApplicationContext;
import com.howie.spring.context.support.ClassPathXMLApplicationContext;
import com.howie.spring.context.support.FileSystemXMLApplicationContext;
import com.howie.springTest.dao.AccountDao;
import com.howie.springTest.sevice.BookStore;
import com.howie.springTest.sevice.PetStoreService;
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
    /**
     * setter注入
     */
    @Test
    public void testClassPathGetBeanBySetter() {
        ApplicationContext context = new ClassPathXMLApplicationContext("bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertTrue(petStoreService.getAccountDao() instanceof AccountDao);

        Assert.assertNotNull(petStoreService.getOwner());
        Assert.assertTrue(petStoreService.getOwner() instanceof String);
        Assert.assertEquals(petStoreService.getOwner(), "howie");
        Assert.assertEquals(petStoreService.getVersion(), 2);

    }

    @Test
    public void testClassPathGetBeanByConstructor() {
        ApplicationContext context = new ClassPathXMLApplicationContext("bean.xml");
        BookStore bookStore = (BookStore) context.getBean("bookStore");

        Assert.assertNotNull(bookStore);

        Assert.assertNotNull(bookStore.getAccountDao());
        Assert.assertNotNull(bookStore.getItemDao());
        Assert.assertNotNull(bookStore.getOwner());

    }

    @Test
    public void testFileSystemGetBean() {
        ApplicationContext context = new FileSystemXMLApplicationContext("src\\test\\resources\\bean.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
