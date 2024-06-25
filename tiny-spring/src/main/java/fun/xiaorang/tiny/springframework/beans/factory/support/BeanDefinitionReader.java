package fun.xiaorang.tiny.springframework.beans.factory.support;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.core.io.Resource;
import fun.xiaorang.tiny.springframework.core.io.ResourceLoader;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 12:01
 */
public interface BeanDefinitionReader {
  BeanDefinitionRegistry getRegistry();

  ResourceLoader getResourceLoader();
  
  void loadBeanDefinitions(Resource resource) throws BeansException;

  void loadBeanDefinitions(Resource... resources) throws BeansException;

  void loadBeanDefinitions(String location) throws BeansException;
}
