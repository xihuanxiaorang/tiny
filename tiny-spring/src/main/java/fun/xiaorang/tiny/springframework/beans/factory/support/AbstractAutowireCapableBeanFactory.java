package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
  private InstantiationStrategy instantiationStrategy;

  public AbstractAutowireCapableBeanFactory() {
    this.instantiationStrategy = new CglibSubclassingInstantiationStrategy();
  }

  @Override
  protected Object createBean(final String beanName, final BeanDefinition beanDefinition, final Object[] args) {
    Object bean;
    try {
      bean = createBeanInstance(beanName, beanDefinition, args);
    } catch (BeansException e) {
      throw new BeansException("Instantiation of bean failed", e);
    }
    registerSingleton(beanName, bean);
    return bean;
  }

  protected Object createBeanInstance(final String beanName, final BeanDefinition beanDefinition, final Object[] args) {
    Constructor<?> constructorToUse = null;
    final Class<?> beanClass = beanDefinition.getBeanClass();
    final Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
    for (final Constructor<?> ctor : declaredConstructors) {
      if (args != null && ctor.getParameterTypes().length == args.length) {
        constructorToUse = ctor;
        break;
      }
    }
    return instantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
  }

  public InstantiationStrategy getInstantiationStrategy() {
    return instantiationStrategy;
  }

  public void setInstantiationStrategy(final InstantiationStrategy instantiationStrategy) {
    this.instantiationStrategy = instantiationStrategy;
  }
}
