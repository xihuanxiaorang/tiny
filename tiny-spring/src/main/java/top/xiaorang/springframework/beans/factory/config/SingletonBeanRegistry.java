package top.xiaorang.springframework.beans.factory.config;

/**
 * @author liulei
 * @description 单实例注册表
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 1:13
 */
public interface SingletonBeanRegistry {
    /**
     * 在给定的bean名称下，在bean注册表中将给定的现有对象注册为单例。
     *
     * @param beanName        bean名称
     * @param singletonObject 单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 根据bean名称获取bean实例对象
     *
     * @param beanName bean名称
     * @return bean实例对象
     */
    Object getSingleton(String beanName);
}
