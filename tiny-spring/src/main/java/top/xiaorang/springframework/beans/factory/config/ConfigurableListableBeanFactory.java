package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.ListableBeanFactory;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:09
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    /**
     * 根据bean名称获取bean定义信息
     *
     * @param beanName bean名称
     * @return bean定义信息
     * @throws BeansException 异常信息
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
