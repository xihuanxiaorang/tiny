package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.core.io.DefaultResourceLoader;
import fun.xiaorang.tiny.springframework.core.io.ResourceLoader;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 12:03
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
  private final BeanDefinitionRegistry registry;
  private final ResourceLoader resourceLoader;


  public AbstractBeanDefinitionReader(final BeanDefinitionRegistry registry) {
    this(registry, new DefaultResourceLoader());
  }

  public AbstractBeanDefinitionReader(final BeanDefinitionRegistry registry, final ResourceLoader resourceLoader) {
    this.registry = registry;
    this.resourceLoader = resourceLoader;
  }

  @Override
  public BeanDefinitionRegistry getRegistry() {
    return this.registry;
  }

  @Override
  public ResourceLoader getResourceLoader() {
    return this.resourceLoader;
  }
}
