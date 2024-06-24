package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:18
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
  private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

  @Override
  public void registerSingleton(final String beanName, final Object singletonObject) {
    singletonObjects.put(beanName, singletonObject);
  }

  @Override
  public Object getSingleton(final String beanName) {
    return singletonObjects.get(beanName);
  }
}
