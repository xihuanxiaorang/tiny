package top.xiaorang.springframework.beans.factory.support;

import top.xiaorang.springframework.beans.factory.config.BeanDefinition;

/**
 * @author liulei
 * @description bean定义信息注册器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:44
 */
public interface BeanDefinitionRegistry {
    /**
     * 注册bean定义信息
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义信息
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * 判断是否存在在指定名称的bean定义信息
     *
     * @param beanName bean名称
     * @return true：是，false：否
     */
    boolean containsBeanDefinition(String beanName);
}
