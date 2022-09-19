package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.BeanFactory;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:01
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    /**
     * 遍历执行 BeanPostProcessor 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean bean实例
     * @param beanName     bean名称
     * @return 修改后的bean实例
     * @throws BeansException 异常信息
     */

    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;

    /**
     * 执行 BeanPostProcessor 接口实现类的 postProcessAfterInitialization 方法
     *
     * @param existingBean bean实例
     * @param beanName     bean名称
     * @return 修改后的bean实例
     * @throws BeansException 异常信息
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;
}
