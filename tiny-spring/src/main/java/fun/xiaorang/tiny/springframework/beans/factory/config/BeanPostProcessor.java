package fun.xiaorang.tiny.springframework.beans.factory.config;

import fun.xiaorang.tiny.springframework.beans.BeansException;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 22:23
 */
public interface BeanPostProcessor {
  Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

  Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
