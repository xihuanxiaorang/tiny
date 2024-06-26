package fun.xiaorang.tiny.springframework.beans.factory;

import fun.xiaorang.tiny.springframework.beans.BeansException;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:15
 */
public interface BeanFactory {
  Object getBean(String beanName) throws BeansException;

  Object getBean(String beanName, Object... args) throws BeansException;

  <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
