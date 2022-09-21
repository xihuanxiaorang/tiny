package top.xiaorang.springframework.aop.framework.adapter;

import cn.hutool.core.lang.Assert;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import top.xiaorang.springframework.aop.MethodBeforeAdvice;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:45
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    private MethodBeforeAdvice advice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice advice) {
        Assert.notNull(advice, "Advice must not be null");
        this.advice = advice;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        // 执行前置通知(增强)
        this.advice.before(mi.getMethod(), mi.getArguments(), mi.getThis());
        /*
         * 调用拦截器的入口 ReflectiveMethodInvocation.proceed()，拦截器链下标将变为 -1，表明当前拦截器为拦截器链中最后一个，
         * 则调用目标方法，并返回目标方法的返回值。
         */
        return mi.proceed();
    }
}
