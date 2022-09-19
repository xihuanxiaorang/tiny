package top.xiaorang.springframework.context;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:56
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @throws BeansException 异常信息
     */
    void refresh() throws BeansException;

    /**
     * 获取bean工厂
     *
     * @return bean工厂
     */
    ConfigurableListableBeanFactory getBeanFactory();
}
