package com.howie.spring.beans.factory.support;

import com.howie.spring.beans.BeanDefinition;
import com.howie.spring.beans.ConstructorArgument;
import com.howie.spring.beans.SimpleTypeConverter;
import com.howie.spring.beans.exception.BeanCreationException;
import com.howie.spring.beans.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 负责注入到构造器
 * @Date 2018-11-19
 * @Time 21:57
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(final BeanDefinition beanDefinition) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;

        Class<?> beanClass;
        //类加载
        try {
            beanClass = this.beanFactory.getBeanClassLoader()
                    .loadClass(beanDefinition.getBeanClassName());

        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getID(),
                    "Instantiation of bean failed, can't resolve class", e);
        }
        //利用反射机制获得该类所有构造器
        Constructor<?>[] candidates = beanClass.getConstructors();

        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);

        ConstructorArgument cargs = beanDefinition.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();
        //遍历找到合适的构造器（匹配到合适的构造器）
        for (int i = 0; i < candidates.length; i++) {
            //检查构成函数参数个数
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != cargs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            //将处理后的参数存放到argsToUse
            boolean result = this.valuesMatchTypes(parameterTypes,
                    cargs.getArgumentValues(),
                    argsToUse,
                    valueResolver,
                    typeConverter);
            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }
        //找不到一个合适的构造函数，抛出异常
        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getID(),
                    "can't find a apporiate constructor");
        }
        try {
            //将argsToUse中的元素注入到构造器中
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getID(),
                    "can't find a create instance using " + constructorToUse);
        }
    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolver valueResolver,
                                     SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder
                    = valueHolders.get(i);
            //获取参数的值，可能是TypedStringValue, 也可能是RuntimeBeanReference
            Object originalValue = valueHolder.getValue();
            try {
                //获得真正的值
                Object resolvedValue = valueResolver.resolveValueIfNecessary(originalValue);
                //如果参数类型是 int, 但是值是字符串,例如"3",还需要转型
                //如果转型失败，则抛出异常。说明这个构造函数不可用
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                //转型成功，记录下来
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
}
