package com.howie.spring.context.support;

import com.howie.spring.core.io.FileSystemResource;
import com.howie.spring.core.io.Resource;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description  ApplicationContext 的实现：处理 FileSystem 的 XML
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
