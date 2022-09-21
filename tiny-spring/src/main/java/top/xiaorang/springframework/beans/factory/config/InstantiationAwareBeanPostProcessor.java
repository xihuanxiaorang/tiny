package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.PropertyValues;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 16:03
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }

    default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return null;
    }
}
