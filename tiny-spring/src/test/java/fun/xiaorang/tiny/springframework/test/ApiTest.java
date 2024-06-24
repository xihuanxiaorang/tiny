package fun.xiaorang.tiny.springframework.test;

import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;
import fun.xiaorang.tiny.springframework.test.bean.UserService;
import org.junit.jupiter.api.Assertions;
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

    // 2. 注册 BeanDefinition
    final BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
    beanFactory.registerBeanDefinition("userService", beanDefinition);

    // 3. 第一次获取 Bean
    final UserService userService = (UserService) beanFactory.getBean("userService");
    userService.queryUserInfo();

    // 4. 第二次获取 Bean From Singleton Cache
    final UserService userService_singleton = (UserService) beanFactory.getBean("userService");
    userService_singleton.queryUserInfo();

    // 5. 判断是否是同一个对象（单例）
    Assertions.assertSame(userService, userService_singleton);
  }
}
