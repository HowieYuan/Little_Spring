package com.howie.spring.test;

import com.howie.spring.core.io.Resourse;
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
        ResourseTest.class
})
public class AllTest {
}
