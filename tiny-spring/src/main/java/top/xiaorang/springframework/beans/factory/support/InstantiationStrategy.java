package top.xiaorang.springframework.beans.factory.support;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author liulei
 * @description bean实例化策略接口
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 4:07
 */
public interface InstantiationStrategy {
    /**
     * 实例化bean对象
     *
     * @param bd       bean定义信息
     * @param beanName bean名称
     * @param ctor     bean的构造器
     * @param args     bean的构造器的参数
     * @return bean实例
     * @throws BeansException 异常信息
     */
    Object instantiate(BeanDefinition bd, String beanName, Constructor<?> ctor, Object... args) throws BeansException;
}
