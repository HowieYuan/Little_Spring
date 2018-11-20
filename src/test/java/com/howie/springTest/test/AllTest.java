package com.howie.springTest.test;

import com.howie.springTest.test.beanFactory.ApplicationContextTest;
import com.howie.springTest.test.beanFactory.BeanFactoryTest;
import com.howie.springTest.test.beanFactory.ResourceTest;
import com.howie.springTest.test.injection.*;
import com.howie.springTest.test.injection.constructor.ConstructorArgumentTest;
import com.howie.springTest.test.injection.constructor.ConstructorResolverTest;
import com.howie.springTest.test.injection.property.PropertyValueTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 1:05
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        BeanFactoryTest.class,
        ApplicationContextTest.class,
        ResourceTest.class,
        PropertyValueTest.class,
        TypeConverterTest.class,
        CustomNumberEditorTest.class,
        ConstructorResolverTest.class,
        ConstructorArgumentTest.class
})
public class AllTest {
}
