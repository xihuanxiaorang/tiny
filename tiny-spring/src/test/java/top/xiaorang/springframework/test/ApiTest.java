package top.xiaorang.springframework.test;

import cn.hutool.core.io.IoUtil;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import top.xiaorang.springframework.aop.MethodMatcher;
import top.xiaorang.springframework.aop.TargetSource;
import top.xiaorang.springframework.aop.aspectj.AspectJExpressionPointcut;
import top.xiaorang.springframework.aop.framework.AdvisedSupport;
import top.xiaorang.springframework.aop.framework.CglibAopProxy;
import top.xiaorang.springframework.aop.framework.JdkDynamicAopProxy;
import top.xiaorang.springframework.aop.framework.ReflectiveMethodInvocation;
import top.xiaorang.springframework.beans.PropertyValue;
import top.xiaorang.springframework.beans.PropertyValues;
import top.xiaorang.springframework.beans.factory.config.BeanDefinition;
import top.xiaorang.springframework.beans.factory.config.BeanReference;
import top.xiaorang.springframework.beans.factory.support.BeanDefinitionReader;
import top.xiaorang.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.xiaorang.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import top.xiaorang.springframework.context.ApplicationContext;
import top.xiaorang.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.springframework.core.io.DefaultResourceLoader;
import top.xiaorang.springframework.core.io.Resource;
import top.xiaorang.springframework.core.io.ResourceLoader;
import top.xiaorang.springframework.test.bean.IUserService;
import top.xiaorang.springframework.test.bean.UserDao;
import top.xiaorang.springframework.test.bean.UserService;
import top.xiaorang.springframework.test.bean.UserServiceInterceptor;
import top.xiaorang.springframework.test.event.CustomEvent;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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
    public void test_beanFactory() {
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
        Resource resource = resourceLoader.getResource("https://github.com/xihuanxiaorang/tiny/blob/tiny-spring/tiny-spring/src/test/resources/important.properties");
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

    @Test
    public void test_beanFactoryPostProcessorAndBeanPostProcessor() {
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_initMethodAndDestroyMethod() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
    }

    @Test
    public void test_hook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("close")));
    }

    @Test
    public void test_aware() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        System.out.println("ApplicationContextAware：" + userService.getApplicationContext());
        System.out.println("BeanFactoryAware：" + userService.getBeanFactory());
    }

    @Test
    public void test_scope() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springPostProcessor.xml");
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.queryUserInfo();
        UserService userService2 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        System.out.println(userService2);
        System.out.println("userDao是否单例对象？" + (userService.getUserDao() == userService2.getUserDao() ? "是" : "否"));
        System.out.println("userService是否单例对象？" + (userService == userService2 ? "是" : "否"));
    }

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springEvent.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.close();
    }

    @Test
    public void test_pointcut() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* top.xiaorang.springframework.test.bean.UserService.queryUserDetail(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getDeclaredMethod("queryUserInfo");
        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }

    @Test
    public void test_aop() {
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(new UserService()));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.xiaorang.springframework.test.bean.IUserService.*(..))"));
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_jdk.queryUserDetail());
        IUserService proxy_cglib = (IUserService) new CglibAopProxy(advisedSupport).getProxy();
        System.out.println("测试结果：" + proxy_cglib.register("小星"));
    }

    /**
     * 当前的AOP就是根据该方法演变过来的
     */
    @Test
    public void test_proxy_method() {
        // 目标对象(可以替换成任何的目标对象)
        Object targetObj = new UserService();

        // AOP 代理
        IUserService proxy = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* top.xiaorang.springframework.test.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObj.getClass())) {
                    // 方法拦截器
                    MethodInterceptor methodInterceptor = invocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return invocation.proceed();
                        } finally {
                            System.out.println("监控 - Begin By AOP");
                            System.out.println("方法名称：" + invocation.getMethod().getName());
                            System.out.println("方法耗时：" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("监控 - End\r\n");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObj, method, args));
                }
                return method.invoke(targetObj, args);
            }
        });

        String result = proxy.queryUserDetail();
        System.out.println("测试结果：" + result);

    }
}
