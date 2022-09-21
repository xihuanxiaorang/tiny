package top.xiaorang.springframework.beans.factory.config;

import top.xiaorang.springframework.beans.factory.HierarchicalBeanFactory;
import top.xiaorang.springframework.util.StringValueResolver;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:07
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    ClassLoader getBeanClassLoader();

    void setBeanClassLoader(ClassLoader beanClassLoader);

    /**
     * 增加bean后置处理器
     *
     * @param beanPostProcessor bean后置处理器
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /**
     * 销毁单例bean对象
     */
    void destroySingletons();

    /**
     * Add a String resolver for embedded values such as annotation attributes.
     *
     * @param valueResolver the String resolver to apply to embedded values
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Determine whether an embedded value resolver has been registered with this
     * bean factory, to be applied through {@link #resolveEmbeddedValue(String)}.
     */
    boolean hasEmbeddedValueResolver();

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     *
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     */
    String resolveEmbeddedValue(String value);
}
