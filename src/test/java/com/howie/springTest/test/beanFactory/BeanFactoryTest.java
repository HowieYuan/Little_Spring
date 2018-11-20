package com.howie.springTest.test.beanFactory;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.exception.BeanCreationException;
import com.howie.spring.beans.exception.BeanDefinitionStoreException;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.springTest.sevice.PetStoreService;
import com.howie.springTest.sevice.SupermarketService;
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
    private DefaultBeanFactory beanFactory = null;
    private XMLBeanDefinitionReader reader = null;

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
        reader.loadBeanDefinition(new ClassPathResource("bean.xml"));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        PetStoreService petStore1 = (PetStoreService) beanFactory.getBean("petStore");

        Assert.assertEquals("com.howie.springTest.sevice.PetStoreService",
                beanDefinition.getBeanClassName());

        Assert.assertNotNull(petStore1);
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinition(new ClassPathResource("bean.xml"));
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
            reader.loadBeanDefinition(new ClassPathResource("xxx.xml"));
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("未抛出BeanDefinitionStoreException");
    }

    @Test
    public void testSingletonBean() {
        reader.loadBeanDefinition(new ClassPathResource("bean.xml"));
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");

        //测试是否标记为单例属性
        Assert.assertTrue(beanDefinition.isSingleton());
        Assert.assertFalse(beanDefinition.isPrototype());
        Assert.assertEquals(beanDefinition.getScope(), BeanDefinition.SCOPE_DEFAULT);

        //测试两次获取的对象是否为同一对象（单例）
        PetStoreService petStore1 = (PetStoreService) beanFactory.getBean("petStore");
        PetStoreService petStore2 = (PetStoreService) beanFactory.getBean("petStore");
        Assert.assertEquals(petStore1, petStore2);
        Assert.assertTrue(petStore1.equals(petStore2));

        //测试两次获取的对象是否为不同对象（非单例）
        SupermarketService supermarket1 = (SupermarketService) beanFactory.getBean("supermarket");
        SupermarketService supermarket2 = (SupermarketService) beanFactory.getBean("supermarket");
        Assert.assertNotEquals(supermarket1, supermarket2);
    }
}
