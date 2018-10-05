package com.howie.spring.beans.factory.xml;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.exception.BeanDefinitionStoreException;
import com.howie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.howie.spring.beans.factory.support.GenericBeanDefinition;
import com.howie.spring.util.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 解析XML读取BeanDefinition
 * @Date 2018-10-05
 * @Time 22:38
 */
public class XMLBeanDefinitionReader {
    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XMLBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * 加载 BeanDefinition, 解析 xml 将 <bean> 数据保存入 beanDefinitionMap
     *
     * @param configFile xml 配置文件
     */
    public void loadBeanDefinition(String configFile) {
        InputStream inputStream = null;
        try {
            //获得类加载器
            ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
            //利用类加载器解析文件获得 InputStream
            inputStream = classLoader.getResourceAsStream(configFile);

            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            //解析 <beans> 标签
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                //解析获得 <bean>，并解析里面的变量
                Element element = iterator.next();
                String id = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                beanDefinitionRegistry.registryBeanDefinition(id, bd);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + configFile, e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
