package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/24 19:13
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
  @Override
  public Object instantiate(final BeanDefinition beanDefinition, final String beanName, final Constructor<?> ctor, final Object[] args) throws BeansException {
    final Class<?> beanClass = beanDefinition.getBeanClass();
    try {
      if (ctor != null) {
        return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
      } else {
        return beanClass.getDeclaredConstructor().newInstance();
      }
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      throw new RuntimeException(e);
    }
  }
}
