package fun.xiaorang.tiny.springframework.beans.factory.config;

import fun.xiaorang.tiny.springframework.beans.PropertyValues;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 22:25
 */
public class BeanDefinition {
  private PropertyValues propertyValues;
  private Class<?> beanClass;

  public BeanDefinition(final Class<?> beanClass) {
    this(beanClass, null);
  }

  public BeanDefinition(final Class<?> beanClass, final PropertyValues propertyValues) {
    this.beanClass = beanClass;
    this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
  }

  public Class<?> getBeanClass() {
    return beanClass;
  }

  public void setBeanClass(final Class<?> beanClass) {
    this.beanClass = beanClass;
  }

  public PropertyValues getPropertyValues() {
    return propertyValues;
  }

  public void setPropertyValues(final PropertyValues propertyValues) {
    this.propertyValues = propertyValues;
  }
}
