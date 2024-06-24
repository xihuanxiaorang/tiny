package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
  @Override
  protected Object createBean(final String beanName, final BeanDefinition beanDefinition) {
    Object bean;
    try {
      bean = beanDefinition.getBeanClass().newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      throw new BeansException("Instantiation of bean failed", e);
    }
    registerSingleton(beanName, bean);
    return bean;
  }
}
