package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.BeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:17
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
  @Override
  public Object getBean(final String beanName) throws BeansException {
    return doGetBean(beanName, null);
  }

  @Override
  public Object getBean(final String beanName, final Object... args) throws BeansException {
    return doGetBean(beanName, args);
  }

  protected Object doGetBean(final String beanName, final Object[] args) throws BeansException {
    final Object bean = getSingleton(beanName);
    if (bean != null) {
      return bean;
    }
    final BeanDefinition beanDefinition = getBeanDefinition(beanName);
    return createBean(beanName, beanDefinition, args);
  }

  protected abstract BeanDefinition getBeanDefinition(final String beanName);

  protected abstract Object createBean(final String beanName, final BeanDefinition beanDefinition, final Object[] args);
}
