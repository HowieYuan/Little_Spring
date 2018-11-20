package com.howie.springTest.test.injection.constructor;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.ConstructorArgument;
import com.howie.spring.beans.factory.config.RuntimeBeanReference;
import com.howie.spring.beans.factory.config.TypedStringValue;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.springTest.sevice.BookStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 构造器注入测试
 * @Date 2018-11-19
 * @Time 21:14
 */
public class ConstructorArgumentTest {
    private DefaultBeanFactory beanFactory = null;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(new ClassPathResource("bean.xml"));
    }

    @Test
    public void constructorArgumentTest() {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("bookStore");
        Assert.assertEquals("com.howie.springTest.sevice.BookStore",
                beanDefinition.getBeanClassName());

        ConstructorArgument args = beanDefinition.getConstructorArgument();

        List<ConstructorArgument.ValueHolder> valueHolders = args.getArgumentValues();

        Assert.assertEquals(3, valueHolders.size());

        RuntimeBeanReference ref1 = (RuntimeBeanReference)valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao", ref1.getBeanName());
        RuntimeBeanReference ref2 = (RuntimeBeanReference)valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao", ref2.getBeanName());

        TypedStringValue strValue = (TypedStringValue)valueHolders.get(2).getValue();
        Assert.assertEquals( "howie", strValue.getValue());
    }
}
