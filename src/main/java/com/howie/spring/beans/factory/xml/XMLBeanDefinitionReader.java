package com.howie.spring.beans.factory.xml;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.exception.BeanDefinitionStoreException;
import com.howie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.howie.spring.beans.factory.support.GenericBeanDefinition;
import com.howie.spring.core.io.Resource;
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

    public static final String SCOPE_ATTRIBUTE = "scope";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XMLBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /**
     * 加载 BeanDefinition, 解析 xml 将 <bean> 数据保存入 beanDefinitionMap
     */
    public void loadBeanDefinition(Resource resource) {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            //解析 <beans> 标签
            Element root = document.getRootElement();
            Iterator<Element> iterator = root.elementIterator();
            while (iterator.hasNext()) {
                //解析获得 <bean>，并解析里面的属性
                Element element = iterator.next();
                //解析 id 属性
                String id = element.attributeValue(ID_ATTRIBUTE);
                //解析 class 属性
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                //解析 scope 属性
                if (element.attribute(SCOPE_ATTRIBUTE) != null) {
                    bd.setScope(element.attributeValue(SCOPE_ATTRIBUTE));
                }
                //使用 BeanDefinitionRegistry 的方法把 bean 注册入 Map 中
                beanDefinitionRegistry.registryBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
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
