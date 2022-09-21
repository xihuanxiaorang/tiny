package top.xiaorang.springframework.test;

import org.junit.Test;
import top.xiaorang.springframework.context.ApplicationContext;
import top.xiaorang.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.springframework.test.bean.IUserService;

/**
 * @author liulei
 * @description 测试Aop功能类
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:53
 */
public class ApiTest3 {
    @Test
    public void test_scan() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springScan.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.register("小星"));
    }

    @Test
    public void test_property() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springProperty.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService);
    }
}
