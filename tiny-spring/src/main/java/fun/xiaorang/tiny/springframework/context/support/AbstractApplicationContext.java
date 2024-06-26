package fun.xiaorang.tiny.springframework.context.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.ConfigurableListableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanFactoryPostProcessor;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanPostProcessor;
import fun.xiaorang.tiny.springframework.context.ConfigurableApplicationContext;
import fun.xiaorang.tiny.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 22:24
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
  @Override
  public void refresh() {
    // 创建BeanFactory并加载BeanDefinition
    refreshBeanFactory();
    // 获取BeanFactory
    final ConfigurableListableBeanFactory beanFactory = getBeanFactory();
    // 执行BeanFactoryPostProcessor
    invokeBeanFactoryPostProcessors(beanFactory);
    // 注册BeanPostProcessor后置处理器
    registerBeanPostProcessors(beanFactory);
    // 提前实例化所有单例Bean
    beanFactory.preInstantiateSingletons();
  }

  @Override
  public <T> Map<String, T> getBeansOfType(final Class<T> type) throws BeansException {
    return getBeanFactory().getBeansOfType(type);
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return getBeanFactory().getBeanDefinitionNames();
  }

  @Override
  public Object getBean(final String beanName) throws BeansException {
    return getBeanFactory().getBean(beanName);
  }

  @Override
  public Object getBean(final String beanName, final Object... args) throws BeansException {
    return getBeanFactory().getBean(beanName, args);
  }

  @Override
  public <T> T getBean(final String name, final Class<T> requiredType) throws BeansException {
    return getBeanFactory().getBean(name, requiredType);
  }

  private void registerBeanPostProcessors(final ConfigurableListableBeanFactory beanFactory) {
    final Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
    for (final BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
      beanFactory.addBeanPostProcessor(beanPostProcessor);
    }
  }

  private void invokeBeanFactoryPostProcessors(final ConfigurableListableBeanFactory beanFactory) {
    final Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
    for (final BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
      beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
    }
  }

  protected abstract void refreshBeanFactory();
}
