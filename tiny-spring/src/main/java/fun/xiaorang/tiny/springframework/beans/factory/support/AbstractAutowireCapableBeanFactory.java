package fun.xiaorang.tiny.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.PropertyValue;
import fun.xiaorang.tiny.springframework.beans.factory.config.AutowireCapableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanPostProcessor;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:24
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {
  private InstantiationStrategy instantiationStrategy;

  public AbstractAutowireCapableBeanFactory() {
    this.instantiationStrategy = new CglibSubclassingInstantiationStrategy();
  }

  @Override
  protected Object createBean(final String beanName, final BeanDefinition beanDefinition, final Object[] args) {
    Object bean;
    try {
      bean = createBeanInstance(beanName, beanDefinition, args);
      applyPropertyValues(beanName, bean, beanDefinition);
      bean = initializeBean(beanName, bean, beanDefinition);
    } catch (BeansException e) {
      throw new BeansException("Instantiation of bean failed", e);
    }
    registerSingleton(beanName, bean);
    return bean;
  }

  @Override
  public Object applyBeanPostProcessorsBeforeInitialization(final Object existingBean, final String beanName) {
    Object result = existingBean;
    for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
      Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
      if (current == null) {
        return result;
      }
      result = current;
    }
    return result;
  }

  @Override
  public Object applyBeanPostProcessorsAfterInitialization(final Object existingBean, final String beanName) {
    Object result = existingBean;
    for (BeanPostProcessor beanPostProcessor : getBeanPostProcessors()) {
      Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
      if (current == null) {
        return result;
      }
      result = current;
    }
    return result;
  }

  protected Object initializeBean(final String beanName, final Object bean, final BeanDefinition beanDefinition) {
    Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
    invokeInitMethods(beanName, wrappedBean, beanDefinition);
    wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
    return wrappedBean;
  }

  private void invokeInitMethods(final String beanName, final Object wrappedBean, final BeanDefinition beanDefinition) {

  }


  protected void applyPropertyValues(final String beanName, final Object bean, final BeanDefinition beanDefinition) {
    try {
      for (final PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
        final String name = propertyValue.getName();
        Object value = propertyValue.getValue();
        if (value instanceof final BeanReference beanReference) {
          value = getBean(beanReference.getBeanName());
        }
        BeanUtil.setFieldValue(bean, name, value);
      }
    } catch (Exception e) {
      throw new BeansException("Error setting property values：" + beanName);
    }
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
