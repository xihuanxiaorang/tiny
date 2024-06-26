package fun.xiaorang.tiny.springframework.test.common;

import fun.xiaorang.tiny.springframework.beans.BeansException;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanPostProcessor;
import fun.xiaorang.tiny.springframework.test.bean.UserService;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/25 23:53
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
  @Override
  public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
    if ("userService".equals(beanName)) {
      UserService userService = (UserService) bean;
      userService.setLocation("改为：北京");
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
    return bean;
  }
}
