package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;

/**
 * @author liulei
 * @description bean后置处理器，用于修改新实例化bean对象的扩展点
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 23:15
 */
public interface BeanPostProcessor {
    /**
     * 在bean对象执行初始化方法之前，执行此方法
     *
     * @param bean     bean对象实例
     * @param beanName bean名称
     * @return 修改后的bean实例
     * @throws BeansException 异常信息
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * 在bean对象执行初始化方法之后，执行此方法
     *
     * @param bean     bean对象实例
     * @param beanName bean名称
     * @return 修改后的bean实例
     * @throws BeansException 异常信息
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
