package top.xiaorang.springframework.beans.factory;

import top.xiaorang.springframework.beans.BeansException;

import java.util.Map;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:02
 */
public interface ListableBeanFactory extends BeanFactory {
    /**
     * 判断是否存在在指定名称的bean定义信息
     *
     * @param beanName bean名称
     * @return true：是，false：否
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * 按照类型返回bean实例
     *
     * @param type bean类型
     * @param <T>  泛型
     * @return bean实例
     * @throws BeansException 异常信息
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 获取注册表中所有的bean名称
     *
     * @return 所有的bean名称
     */
    String[] getBeanDefinitionNames();
}
