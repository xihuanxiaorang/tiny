package top.xiaorang.springframework.beans.factory.support;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.DisposableBean;
import top.xiaorang.springframework.beans.factory.FactoryBean;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.config.BeanPostProcessor;
import top.xiaorang.springframework.beans.factory.config.ConfigurableBeanFactory;
import top.xiaorang.springframework.util.ClassUtils;
import top.xiaorang.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liulei
 * @description 定义getBean的整体流程
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:30
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * String resolvers to apply e.g. to annotation attribute values.
     */
    private final List<StringValueResolver> embeddedValueResolvers = new CopyOnWriteArrayList<>();

    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public Object getBean(String name) throws BeansException {
        return doGetBean(name, null);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return doGetBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    /**
     * 真正用来获取bean实例的方法
     *
     * @param name bean名称
     * @param args bean构造器参数
     * @param <T>  泛型
     * @return bean实例
     */
    protected <T> T doGetBean(String name, Object[] args) {
        Object sharedInstance = getSingleton(name);
        if (sharedInstance != null) {
            return (T) getObjectForBeanInstance(sharedInstance, name);
        }
        BeanDefinition beanDefinition = getBeanDefinition(name);
        Object bean = createBean(name, beanDefinition, args);
        return (T) getObjectForBeanInstance(bean, name);
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object object = getCachedObjectForFactoryBean(beanName);
        if (object == null) {
            FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factory, beanName);
        }
        return object;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        Assert.notNull(beanPostProcessor, "BeanPostProcessor must not be null");
        // Remove from old position, if any
        this.beanPostProcessors.remove(beanPostProcessor);
        // Add to end of list
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader beanClassLoader) {
        this.beanClassLoader = (beanClassLoader != null ? beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        Assert.notNull(valueResolver, "StringValueResolver must not be null");
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public boolean hasEmbeddedValueResolver() {
        return !this.embeddedValueResolvers.isEmpty();
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        if (value == null) {
            return null;
        }
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
            if (result == null) {
                return null;
            }
        }
        return result;
    }

    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition) {
        if (!beanDefinition.isSingleton()) return;
        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    /**
     * 根据bean名称获取bean定义信息
     *
     * @param beanName bean名称
     * @return bean定义信息
     * @throws BeansException 异常信息
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean实例
     *
     * @param beanName       bean名称
     * @param beanDefinition bean定义信息
     * @param args           bean构造器参数
     * @return bean实例
     * @throws BeansException 异常信息
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
