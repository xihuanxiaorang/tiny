package top.xiaorang.springframework.beans.factory;

import top.xiaorang.springframework.beans.BeansException;

/**
 * @author liulei
 * @description bean工厂
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 1:24
 */
public interface BeanFactory {
    /**
     * 根据bean名称获取bean实例对象
     *
     * @param name bean名称
     * @return bean实例对象
     * @throws BeansException bean异常信息
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据bean名称和构造器参数获取bean实例对象
     *
     * @param name bean名称
     * @param args bean构造器参数
     * @return bean实例对象
     * @throws BeansException bean异常信息
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * 根据bean名称和构造器参数获取bean实例对象
     *
     * @param name         bean名称
     * @param requiredType bean实例类型
     * @param <T>          泛型
     * @return bean实例对象
     * @throws BeansException 异常信息
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
