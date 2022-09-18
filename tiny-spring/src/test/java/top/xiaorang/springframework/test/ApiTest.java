package top.xiaorang.springframework.test;

import org.junit.Test;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.xiaorang.springframework.test.bean.UserService;

/**
 * @author liulei
 * @description 测试类
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:53
 */
public class ApiTest {
    @Test
    public void testBeanFactory() {
        // 创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册bean定义信息
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class));
        // 获取bean实例
        UserService userService = (UserService) beanFactory.getBean("userService", "小让");
        userService.queryUserInfo();
    }
}
