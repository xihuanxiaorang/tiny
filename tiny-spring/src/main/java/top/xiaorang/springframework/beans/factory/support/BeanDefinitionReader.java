package top.xiaorang.springframework.beans.factory.support;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.core.io.Resource;
import top.xiaorang.springframework.core.io.ResourceLoader;

/**
 * @author liulei
 * @description bean定义信息读取器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 16:38
 */
public interface BeanDefinitionReader {
    /**
     * 获取bean定义信息注册器
     *
     * @return bean定义信息注册器
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     *
     * @return 资源加载器
     */
    ResourceLoader getResourceLoader();

    /**
     * 通过资源加载bean定义信息
     *
     * @param resource 资源
     * @throws BeansException 异常信息
     */
    void loadBeanDefinitions(Resource resource) throws BeansException;

    /**
     * 通过资源集合加载bean定义信息
     *
     * @param resources 资源集合
     * @throws BeansException 异常信息
     */
    void loadBeanDefinitions(Resource... resources) throws BeansException;

    /**
     * 通过资源路径加载bean定义信息
     *
     * @param location 资源路径
     * @throws BeansException 异常信息
     */
    void loadBeanDefinitions(String location) throws BeansException;

    /**
     * 通过资源路径集合加载bean定义信息
     *
     * @param locations 资源路径集合
     * @throws BeansException 异常信息
     */
    void loadBeanDefinitions(String... locations) throws BeansException;
}
