package top.xiaorang.springframework.context.support;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.config.BeanFactoryPostProcessor;
import top.xiaorang.springframework.beans.factory.config.BeanPostProcessor;
import top.xiaorang.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import top.xiaorang.springframework.context.ApplicationEvent;
import top.xiaorang.springframework.context.ApplicationListener;
import top.xiaorang.springframework.context.ConfigurableApplicationContext;
import top.xiaorang.springframework.context.event.ApplicationEventMulticaster;
import top.xiaorang.springframework.context.event.ContextClosedEvent;
import top.xiaorang.springframework.context.event.ContextRefreshedEvent;
import top.xiaorang.springframework.context.event.SimpleApplicationEventMulticaster;
import top.xiaorang.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 22:58
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
        prepareBeanFactory(beanFactory);
        invokeBeanFactoryPostProcessors(beanFactory);
        registerBeanPostProcessors(beanFactory);
        initApplicationEventMulticaster();
        registerListeners();
        beanFactory.preInstantiateSingletons();
        finishRefresh();
    }

    /**
     * 完成容器刷新
     * 发布容器刷新完成事件
     */
    protected void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * 注册事件监听器
     */
    protected void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            getApplicationEventMulticaster().addApplicationListener(applicationListener);
        }
    }

    /**
     * 初始化事件多播器
     */
    protected void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
    }

    /**
     * 预准备工厂
     * 向容器中添加一个ApplicationContextAwareProcessor的bean后置处理器
     *
     * @param beanFactory bean工厂
     */
    protected void prepareBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        beanFactory.setBeanClassLoader(getClassLoader());
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
    }

    /**
     * 注册所有的bean后置处理器BeanPostProcessor
     *
     * @param beanFactory bean工厂
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessors = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 执行所有bean工厂后置处理器
     *
     * @param beanFactory bean工厂
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessors = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessors.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 获取并刷新容器
     *
     * @return bean工厂
     */
    protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
        refreshBeanFactory();
        return getBeanFactory();
    }

    /**
     * 创建 BeanFactory，并将配置文件中的bean定义信息注册进工厂中，保存到 beanDefinitionMap 中
     *
     * @throws BeansException        异常信息
     * @throws IllegalStateException 异常信息
     */
    protected abstract void refreshBeanFactory() throws BeansException, IllegalStateException;

    /**
     * 获取bean工厂
     *
     * @return bean工厂
     */
    public abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return getBeanFactory().containsBeanDefinition(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        publishEvent(new ContextClosedEvent(this));
        getBeanFactory().destroySingletons();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        getApplicationEventMulticaster().multicastEvent(event);
    }

    ApplicationEventMulticaster getApplicationEventMulticaster() throws IllegalStateException {
        if (this.applicationEventMulticaster == null) {
            throw new IllegalStateException("ApplicationEventMulticaster not initialized - " +
                    "call 'refresh' before multicasting events via the context: " + this);
        }
        return this.applicationEventMulticaster;
    }
}
