package com.howie.spring.beans.factory.xml;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.PropertyValue;
import com.howie.spring.beans.exception.BeanDefinitionStoreException;
import com.howie.spring.beans.factory.config.RuntimeBeanReference;
import com.howie.spring.beans.factory.config.TypedStringValue;
import com.howie.spring.beans.factory.support.BeanDefinitionRegistry;
import com.howie.spring.beans.factory.support.GenericBeanDefinition;
import com.howie.spring.core.io.Resource;
import com.howie.spring.util.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
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

    public static final String PROPERTY_ELEMENT = "property";

    public static final String REF_ATTRIBUTE = "ref";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String NAME_ATTRIBUTE = "name";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    protected final Log logger = LogFactory.getLog(getClass());

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
                parsePropertyElement(element, bd);
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

    /**
     * 解析bean的property元素
     */
    private void parsePropertyElement(Element element, BeanDefinition definition) {
        Iterator iterator = element.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propertyElement = (Element) iterator.next();
            String propertyName = propertyElement.attributeValue(NAME_ATTRIBUTE);
            //是否存在name属性
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object value = parsePropertyValue(propertyElement, definition, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, value);

            definition.getPropertyValues().add(propertyValue);
        }
    }

    /**
     * 解析property元素的value属性
     */
    private Object parsePropertyValue(Element propertyElement, BeanDefinition definition,
                                      String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";

        boolean hasRefAttribute = (propertyElement.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (propertyElement.attribute(VALUE_ATTRIBUTE) != null);

        //如果是 ref 属性
        if (hasRefAttribute) {
            String refName = propertyElement.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            return new RuntimeBeanReference(refName);
            //如果是 value 属性
        } else if (hasValueAttribute) {
            return new TypedStringValue(propertyElement.attributeValue(VALUE_ATTRIBUTE));
        } else {
            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
