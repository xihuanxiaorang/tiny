package top.xiaorang.springframework.aop.framework.autoproxy;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import top.xiaorang.springframework.aop.Advisor;
import top.xiaorang.springframework.aop.ClassFilter;
import top.xiaorang.springframework.aop.Pointcut;
import top.xiaorang.springframework.aop.TargetSource;
import top.xiaorang.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import top.xiaorang.springframework.aop.framework.ProxyFactory;
import top.xiaorang.springframework.beans.BeansException;
import top.xiaorang.springframework.beans.factory.BeanFactory;
import top.xiaorang.springframework.beans.factory.BeanFactoryAware;
import top.xiaorang.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import top.xiaorang.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.xiaorang.springframework.util.ClassUtils;

import java.util.Collection;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 16:07
 */
public abstract class AbstractAutoProxyCreator implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {
    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clazz = ClassUtils.getUserClass(bean);
        if (isInfrastructureClass(clazz)) {
            return bean;
        }
        // TODO 这里与Spring官方源码中有点不一样
        Collection<AspectJExpressionPointcutAdvisor> advisors = this.beanFactory.getBeansOfType(AspectJExpressionPointcutAdvisor.class).values();
        for (AspectJExpressionPointcutAdvisor advisor : advisors) {
            ClassFilter classFilter = advisor.getPointcut().getClassFilter();
            if (!classFilter.matches(clazz)) continue;
            ProxyFactory proxyFactory = new ProxyFactory();
            proxyFactory.setProxyTargetClass(false);
            proxyFactory.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            proxyFactory.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            proxyFactory.setTargetSource(new TargetSource(bean));
            return proxyFactory.getProxy();
        }
        return bean;
    }

    protected boolean isInfrastructureClass(Class<?> beanClass) {
        /*
         * 假如当前正在创建的 bean 的 class 是 Advice、Pointcut、Advisor 类型，则直接跳过，不需要解析
         */
        return Advice.class.isAssignableFrom(beanClass) ||
                Pointcut.class.isAssignableFrom(beanClass) ||
                Advisor.class.isAssignableFrom(beanClass);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }
}
