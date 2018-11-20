package com.howie.springTest.test.injection.constructor;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.factory.support.ConstructorResolver;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.Resource;
import com.howie.springTest.sevice.BookStore;
import com.howie.springTest.sevice.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-11-19
 * @Time 21:55
 */
public class ConstructorResolverTest {
    @Test
    public void testAutowireConstructor() {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("bean.xml");
        reader.loadBeanDefinition(resource);

        BeanDefinition bd = factory.getBeanDefinition("bookStore");

        ConstructorResolver resolver = new ConstructorResolver(factory);

        BookStore bookStore = (BookStore)resolver.autowireConstructor(bd);

        // 验证参数version 正确地通过此构造函数做了初始化
        // PetStoreService(AccountDao accountDao, ItemDao itemDao,int version)
        Assert.assertEquals("howie", bookStore.getOwner());

        Assert.assertNotNull(bookStore.getAccountDao());
        Assert.assertNotNull(bookStore.getItemDao());


    }
}
