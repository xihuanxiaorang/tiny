package fun.xiaorang.tiny.springframework.test;

import fun.xiaorang.tiny.springframework.beans.PropertyValue;
import fun.xiaorang.tiny.springframework.beans.PropertyValues;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanReference;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;
import fun.xiaorang.tiny.springframework.test.bean.UserDao;
import fun.xiaorang.tiny.springframework.test.bean.UserService;
import org.junit.jupiter.api.Test;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:42
 */
public class ApiTest {
  @Test
  public void test_BeanFactory() {
    // 1. 初始化 BeanFactory
    final DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2. 注册 UserDao BeanDefinition
    beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

    // 3. UserService 属性设置
    PropertyValues propertyValues = new PropertyValues();
    propertyValues.addPropertyValue(new PropertyValue("userId", "10001"));
    propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

    // 4. 注册 UserService BeanDefinition
    final BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
    beanFactory.registerBeanDefinition("userService", beanDefinition);

    // 5. 获取 UserService Bean
    final UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();
  }
}
