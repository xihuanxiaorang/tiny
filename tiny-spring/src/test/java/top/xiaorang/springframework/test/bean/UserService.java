package top.xiaorang.springframework.test.bean;

import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.BeanClassLoaderAware;
import top.xiaorang.springframework.beans.factory.BeanFactory;
import top.xiaorang.springframework.beans.factory.BeanFactoryAware;
import top.xiaorang.springframework.beans.factory.BeanNameAware;
import top.xiaorang.springframework.beans.factory.DisposableBean;
import top.xiaorang.springframework.beans.factory.InitializingBean;
import top.xiaorang.springframework.context.ApplicationContext;
import top.xiaorang.springframework.context.ApplicationContextAware;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:54
 */
public class UserService implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String userId;
    private String location;
    private String company;
    private IUserDao userDao;

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader：" + classLoader);
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("beanName：" + name);
    }

    public void queryUserInfo() {
        System.out.println("查询用户信息：" + userDao.queryUserName(userId) + "," + company + "," + location);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "userId='" + userId + '\'' +
                ", location='" + location + '\'' +
                ", company='" + company + '\'' +
                ", userDao=" + userDao +
                '}';
    }
}
