package fun.xiaorang.tiny.springframework.context.support;

import fun.xiaorang.tiny.springframework.beans.factory.ConfigurableListableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 23:25
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
  private DefaultListableBeanFactory beanFactory;

  @Override
  protected void refreshBeanFactory() {
    final DefaultListableBeanFactory beanFactory = createBeanFactory();
    loadBeanDefinitions(beanFactory);
    this.beanFactory = beanFactory;
  }

  protected abstract void loadBeanDefinitions(final DefaultListableBeanFactory beanFactory);

  private DefaultListableBeanFactory createBeanFactory() {
    return new DefaultListableBeanFactory();
  }

  @Override
  public ConfigurableListableBeanFactory getBeanFactory() {
    return this.beanFactory;
  }
}
