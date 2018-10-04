package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.util.ClassUtils;
import com.howie.spring.beans.factory.BeanFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description
 * @Date 2018-10-04
 * @Time 17:30
 */
public class DefaultBeanFactory implements BeanFactory {
    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    /**
     * xml 文件中各个 <bean>
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>(64);

    /**
     * @param configFile xml 配置文件
     */
    public DefaultBeanFactory(String configFile) {
        loadBeanDefinition(configFile);
    }

    /**
     * 加载 BeanDefinition, 解析 xml 将 <bean> 数据保存入 beanDefinitionMap
     *
     * @param configFile xml 配置文件
     */
    private void loadBeanDefinition(String configFile) {
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
                this.beanDefinitionMap.put(id, bd);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
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

    @Override
    public BeanDefinition getBeanDefinition(String beanID) {
        return this.beanDefinitionMap.get(beanID);
    }

    @Override
    public Object getBean(String beanID) {
        //GenericBeanDefinition
        BeanDefinition bd = this.getBeanDefinition(beanID);
        if (bd == null) {
            return null;
        }
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        String beanClassName = bd.getBeanClassName();
        try {
            Class<?> clz = cl.loadClass(beanClassName);
            return clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {

            e.printStackTrace();
        } catch (IllegalAccessException e) {

            e.printStackTrace();
        }

        return null;
    }
}
