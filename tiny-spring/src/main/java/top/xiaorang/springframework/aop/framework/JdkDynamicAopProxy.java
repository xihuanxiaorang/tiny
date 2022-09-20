package top.xiaorang.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import top.xiaorang.springframework.aop.TargetSource;
import top.xiaorang.springframework.util.ClassUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:32
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(ClassUtils.getDefaultClassLoader(), advised.getTargetSource().getTargetInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        TargetSource targetSource = advised.getTargetSource();
        Object target = targetSource.getTarget();
        Class<?> targetClass = targetSource.getTargetClass();
        if (advised.getMethodMatcher().matches(method, targetClass)) {
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(target, method, args));
        }
        return method.invoke(target, args);
    }
}
