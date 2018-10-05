package com.howie.spring.test;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.exception.BeanCreationException;
import com.howie.spring.beans.exception.BeanDefinitionStoreException;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.sevice.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-04
 * @Time 17:28
 */
public class BeanFactoryTest {
    DefaultBeanFactory beanFactory = null;
    XMLBeanDefinitionReader reader = null;

    /**
     * 每开始一个测试用例都重新加载一次 DefaultBeanFactory 和 XMLBeanDefinitionReader
     */
    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        reader = new XMLBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinition("bean.xml");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        Assert.assertEquals("com.howie.spring.sevice.PetStoreService",
                beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinition("bean.xml");
        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("未抛出BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            reader.loadBeanDefinition("xxx.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("未抛出BeanDefinitionStoreException");
    }
}
