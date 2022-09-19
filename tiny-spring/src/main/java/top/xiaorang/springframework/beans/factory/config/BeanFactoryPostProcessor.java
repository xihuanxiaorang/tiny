package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;

/**
 * @author liulei
 * @description bean工厂后置处理器
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 23:07
 */
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
