package com.howie.springTest.test.injection.property;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.PropertyValue;
import com.howie.spring.beans.factory.config.RuntimeBeanReference;
import com.howie.spring.beans.factory.config.TypedStringValue;
import com.howie.spring.beans.factory.support.BeanDefinitionValueResolver;
import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.springTest.dao.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description bean的property元素的测试（setter）
 * @Date 2018-10-10
 * @Time 17:50
 */
public class PropertyValueTest {
    private DefaultBeanFactory beanFactory = null;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(new ClassPathResource("bean.xml"));
    }


    /**
     * 测试bean的property元素的获取
     */
    @Test
    public void propertyValueTest() {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStore");
        List<PropertyValue> list = beanDefinition.getPropertyValues();

        Assert.assertEquals(list.size(), 4);
        PropertyValue propertyValue1 = this.getPropertyValue("accountDao", list);
        Assert.assertNotNull(propertyValue1);
        Assert.assertTrue(propertyValue1.getValue() instanceof RuntimeBeanReference);

        PropertyValue propertyValue2 = this.getPropertyValue("itemDao", list);
        Assert.assertNotNull(propertyValue2);
        Assert.assertTrue(propertyValue2.getValue() instanceof RuntimeBeanReference);
    }

    /**
     * 测试property元素的中 ref 属性实例值的生成
     */
    @Test
    public void ResolveRuntimeBeanReferenceTest() {
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(beanFactory);
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }

    /**
     * 测试property元素的中 value 属性值的生成
     */
    @Test
    public void ResolveTypedStringValueTest() {
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(beanFactory);
        TypedStringValue typedStringValue = new TypedStringValue("howie");
        Object value = resolver.resolveValueIfNecessary(typedStringValue);

        Assert.assertNotNull(value);
        Assert.assertTrue(value.equals("howie"));
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> list) {
        for (PropertyValue value : list) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
