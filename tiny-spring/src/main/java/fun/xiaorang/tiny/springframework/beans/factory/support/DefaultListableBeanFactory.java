package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.ConfigurableListableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:29
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
  private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);

  @Override
  public void registerBeanDefinition(final String beanName, final BeanDefinition beanDefinition) {
    beanDefinitionMap.put(beanName, beanDefinition);
  }

  @Override
  public BeanDefinition getBeanDefinition(final String beanName) {
    final BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
    if (beanDefinition == null) {
      throw new BeansException("No bean named '" + beanName + "' is defined");
    }
    return beanDefinition;
  }

  @Override
  public void preInstantiateSingletons() throws BeansException {
    beanDefinitionMap.keySet().forEach(this::getBean);
  }

  @Override
  public boolean containsBeanDefinition(final String beanName) {
    return beanDefinitionMap.containsKey(beanName);
  }

  @Override
  public <T> Map<String, T> getBeansOfType(final Class<T> type) throws BeansException {
    Map<String, T> result = new HashMap<>();
    beanDefinitionMap.forEach((beanName, beanDefinition) -> {
      final Class<?> beanClass = beanDefinition.getBeanClass();
      if (type.isAssignableFrom(beanClass)) {
        result.put(beanName, getBean(beanName, type));
      }
    });
    return result;
  }

  @Override
  public String[] getBeanDefinitionNames() {
    return beanDefinitionMap.keySet().toArray(new String[0]);
  }
}
