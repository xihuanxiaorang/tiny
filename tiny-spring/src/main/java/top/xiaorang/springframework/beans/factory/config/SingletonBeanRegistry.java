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
     * 根据bean名称获取bean实例对象
     *
     * @param beanName bean名称
     * @return bean实例对象
     */
    Object getSingleton(String beanName);
}
