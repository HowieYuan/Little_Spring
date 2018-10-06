package com.howie.spring.context.support;

import com.howie.spring.beans.factory.support.DefaultBeanFactory;
import com.howie.spring.beans.factory.xml.XMLBeanDefinitionReader;
import com.howie.spring.context.ApplicationContext;
import com.howie.spring.core.io.ClassPathResource;
import com.howie.spring.core.io.FileSystemResource;
import com.howie.spring.core.io.Resource;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-06
 * @Time 14:04
 */
public class FileSystemXMLApplicationContext extends AbstractApplicationContext {

    public FileSystemXMLApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResource(String configFile) {
        return new FileSystemResource(configFile);
    }
}
