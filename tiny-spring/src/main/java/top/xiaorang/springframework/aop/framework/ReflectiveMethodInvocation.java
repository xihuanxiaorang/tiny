package top.xiaorang.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:38
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object target;

    protected final Method method;
    protected Object[] arguments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }

    @Override
    public final Object getThis() {
        return this.target;
    }

    @Override
    public final AccessibleObject getStaticPart() {
        return this.method;
    }

    @Override
    public final Method getMethod() {
        return this.method;
    }

    @Override
    public final Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(this.target, this.arguments);
    }
}
