package com.howie.spring.context.support;

import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.Resource;


/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description ApplicationContext 的实现：处理 ClassPath 的 XML
 * @Date 2018-10-06
 * @Time 0:59
 */
public class ClassPathXMLApplicationContext extends AbstractApplicationContext {

    public ClassPathXMLApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResource(String configFile) {
        return new ClassPathResource(configFile, this.getBeanClassLoader());
    }
}
