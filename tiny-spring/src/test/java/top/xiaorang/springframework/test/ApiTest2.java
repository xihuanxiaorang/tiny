package top.xiaorang.springframework.test;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import top.xiaorang.springframework.aop.ClassFilter;
import top.xiaorang.springframework.aop.TargetSource;
import top.xiaorang.springframework.aop.aspectj.AspectJExpressionPointcut;
import top.xiaorang.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import top.xiaorang.springframework.aop.framework.ProxyFactory;
import top.xiaorang.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import top.xiaorang.springframework.context.support.ClassPathXmlApplicationContext;
import top.xiaorang.springframework.test.bean.IUserService;
import top.xiaorang.springframework.test.bean.UserService;
import top.xiaorang.springframework.test.bean.UserServiceBeforeAdvice;
import top.xiaorang.springframework.test.bean.UserServiceInterceptor;

/**
 * @author liulei
 * @description 测试Aop功能类
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/19 2:53
 */
public class ApiTest2 {
    private ProxyFactory proxyFactory;

    @Before
    public void init() {
        proxyFactory = new ProxyFactory();
        proxyFactory.setTargetSource(new TargetSource(new UserService()));
        proxyFactory.setMethodInterceptor(new UserServiceInterceptor());
        proxyFactory.setMethodMatcher(new AspectJExpressionPointcut("execution(* top.xiaorang.springframework.test.bean.IUserService.*(..))"));
    }

    @Test
    public void test_proxyFactory() {
        proxyFactory.setProxyTargetClass(false); // false/true，JDK动态代理、CGlib动态代理
        IUserService userService = (IUserService) proxyFactory.getProxy();
        System.out.println("测试结果：" + userService.queryUserDetail());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice userServiceBeforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(userServiceBeforeAdvice);
        proxyFactory.setMethodInterceptor(methodBeforeAdviceInterceptor);
        IUserService userService = (IUserService) proxyFactory.getProxy();
        System.out.println("测试结果：" + userService.queryUserDetail());
    }

    @Test
    public void test_advisor() {
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* top.xiaorang.springframework.test.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            ProxyFactory proxyFactory1 = new ProxyFactory();
            proxyFactory1.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            proxyFactory1.setTargetSource(new TargetSource(userService));
            proxyFactory1.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            proxyFactory1.setProxyTargetClass(true);
            IUserService proxy = (IUserService) proxyFactory1.getProxy();
            System.out.println("测试结果：" + proxy.queryUserDetail());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springAop.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.register("小星"));
    }
}
