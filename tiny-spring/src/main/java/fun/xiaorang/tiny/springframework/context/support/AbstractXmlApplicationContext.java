package fun.xiaorang.tiny.springframework.context.support;

import fun.xiaorang.tiny.springframework.beans.factory.support.BeanDefinitionReader;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 23:33
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableConfigApplicationContext {
  @Override
  protected void loadBeanDefinitions(final DefaultListableBeanFactory beanFactory) {
    BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
    if (getConfigLocations() != null) {
      beanDefinitionReader.loadBeanDefinitions(getConfigLocations());
    }
  }
}
