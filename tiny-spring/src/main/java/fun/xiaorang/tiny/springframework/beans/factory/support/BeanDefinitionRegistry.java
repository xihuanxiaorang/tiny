package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:30
 */
public interface BeanDefinitionRegistry {
  void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

  BeanDefinition getBeanDefinition(String beanName);

  boolean containsBeanDefinition(String beanName);
}
