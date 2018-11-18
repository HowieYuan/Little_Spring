package com.howie.springTest.test;

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
        CustomNumberEditorTest.class
})
public class AllTest {
}
