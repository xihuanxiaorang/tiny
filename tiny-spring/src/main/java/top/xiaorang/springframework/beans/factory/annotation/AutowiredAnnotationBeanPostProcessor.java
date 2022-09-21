package top.xiaorang.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.PropertyValues;
import top.xiaorang.springframework.beans.factory.BeanFactory;
import top.xiaorang.springframework.beans.factory.BeanFactoryAware;
import top.xiaorang.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import top.xiaorang.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import top.xiaorang.springframework.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/22 2:05
 */
public class AutowiredAnnotationBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Class<?> clazz = ClassUtils.getUserClass(bean);
        Field[] fields = clazz.getDeclaredFields();

        // 1. 处理注解 @Value
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (valueAnnotation != null) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2. 处理注解 @Autowired
        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (autowiredAnnotation != null) {
                Class<?> fieldType = field.getType();
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean;
                if (qualifierAnnotation != null) {
                    String dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        
        return pvs;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
            throw new IllegalArgumentException(
                    "AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
        }
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }
}
