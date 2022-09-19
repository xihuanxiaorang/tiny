package top.xiaorang.springframework.test;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;
import top.xiaorang.springframework.beans.PropertyValue;
import top.xiaorang.springframework.beans.PropertyValues;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.config.BeanReference;
import top.xiaorang.springframework.beans.factory.support.BeanDefinitionReader;
import top.xiaorang.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.xiaorang.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.xiaorang.springframework.core.io.DefaultResourceLoader;
import top.xiaorang.springframework.core.io.Resource;
import top.xiaorang.springframework.core.io.ResourceLoader;
import top.xiaorang.springframework.test.bean.UserDao;
import top.xiaorang.springframework.test.bean.UserService;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liulei
 * @description 测试类
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:53
 */
public class ApiTest {
    private ResourceLoader resourceLoader;

    @Before
    public void init() {
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_BeanFactory() {
        // 创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册UserDao的bean定义信息
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        // UserService设置属性[userId、userDao]
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("userId", "1002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        // 注册UserService的bean定义信息
        beanFactory.registerBeanDefinition("userService", new BeanDefinition(UserService.class, propertyValues));
        // 获取UserService的bean实例
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_classpath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_file() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_url() throws IOException {
        Resource resource = resourceLoader.getResource("https://github.com/xihuanxiaorang/tiny/blob/master/tiny-spring/src/test/resource/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_xml() {
        // 创建bean工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 读取配置文件&注册Bean
        BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        // 获取UserService的bean实例
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
