package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanPostProcessor;
import fun.xiaorang.tiny.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:17
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
  private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

  @Override
  public Object getBean(final String beanName) throws BeansException {
    return doGetBean(beanName, null);
  }

  @Override
  public Object getBean(final String beanName, final Object... args) throws BeansException {
    return doGetBean(beanName, args);
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T getBean(final String name, final Class<T> requiredType) throws BeansException {
    return (T) getBean(name);
  }

  @Override
  public void addBeanPostProcessor(final BeanPostProcessor beanPostProcessor) {
    this.beanPostProcessors.remove(beanPostProcessor);
    this.beanPostProcessors.add(beanPostProcessor);
  }

  public List<BeanPostProcessor> getBeanPostProcessors() {
    return this.beanPostProcessors;
  }

  @SuppressWarnings("unchecked")
  protected <T> T doGetBean(final String beanName, final Object[] args) throws BeansException {
    final Object bean = getSingleton(beanName);
    if (bean != null) {
      return (T) bean;
    }
    final BeanDefinition beanDefinition = getBeanDefinition(beanName);
    return (T) createBean(beanName, beanDefinition, args);
  }

  protected abstract BeanDefinition getBeanDefinition(final String beanName);

  protected abstract Object createBean(final String beanName, final BeanDefinition beanDefinition, final Object[] args);
}
