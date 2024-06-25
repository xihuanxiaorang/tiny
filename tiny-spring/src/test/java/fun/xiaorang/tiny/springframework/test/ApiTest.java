package fun.xiaorang.tiny.springframework.test;

import cn.hutool.core.io.IoUtil;
import fun.xiaorang.tiny.springframework.beans.PropertyValue;
import fun.xiaorang.tiny.springframework.beans.PropertyValues;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanDefinition;
import fun.xiaorang.tiny.springframework.beans.factory.config.BeanReference;
import fun.xiaorang.tiny.springframework.beans.factory.support.BeanDefinitionReader;
import fun.xiaorang.tiny.springframework.beans.factory.support.DefaultListableBeanFactory;
import fun.xiaorang.tiny.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import fun.xiaorang.tiny.springframework.core.io.DefaultResourceLoader;
import fun.xiaorang.tiny.springframework.core.io.Resource;
import fun.xiaorang.tiny.springframework.core.io.ResourceLoader;
import fun.xiaorang.tiny.springframework.test.bean.UserDao;
import fun.xiaorang.tiny.springframework.test.bean.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaorang
 * @description <p style = " font-weight:bold ; "><p/>
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://docs.xiaorang.fun">小让的糖果屋</a>  - show me the code
 * @date 2024/06/23 23:42
 */
public class ApiTest {
  private ResourceLoader resourceLoader;

  @BeforeEach
  public void before() {
    resourceLoader = new DefaultResourceLoader();
  }

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
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
  }

  @Test
  public void test_classpath() throws IOException {
    final Resource resource = resourceLoader.getResource("classpath:important.properties");
    final InputStream inputStream = resource.getInputStream();
    final String content = IoUtil.readUtf8(inputStream);
    System.out.println(content);
  }

  @Test
  public void test_file() throws IOException {
    final Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
    final InputStream inputStream = resource.getInputStream();
    final String content = IoUtil.readUtf8(inputStream);
    System.out.println(content);
  }

  @Test
  public void test_url() throws IOException {
    final Resource resource = resourceLoader.getResource(" https://github.com/xihuanxiaorang/tiny/blob/main/tiny-spring/src/test/resources/important.properties");
    final InputStream inputStream = resource.getInputStream();
    final String content = IoUtil.readUtf8(inputStream);
    System.out.println(content);
  }

  @Test
  public void test_xml() {
    // 1. 初始化 BeanFactory
    DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

    // 2.读取xml配置文件&注册BeanDefinition
    BeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, resourceLoader);
    beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

    // 3. 获取 UserService Bean
    final UserService userService = beanFactory.getBean("userService", UserService.class);
    String result = userService.queryUserInfo();
    System.out.println("测试结果：" + result);
  }
}
