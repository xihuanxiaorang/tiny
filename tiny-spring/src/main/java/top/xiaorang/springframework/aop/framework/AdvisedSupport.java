package top.xiaorang.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import top.xiaorang.springframework.aop.MethodMatcher;
import top.xiaorang.springframework.aop.TargetSource;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:27
 */
public class AdvisedSupport {
    /**
     * 被代理的目标对象
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器(检查目标方法是否符合通知条件)
     */
    private MethodMatcher methodMatcher;

    public AdvisedSupport() {
    }

    public AdvisedSupport(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        this.targetSource = targetSource;
        this.methodInterceptor = methodInterceptor;
        this.methodMatcher = methodMatcher;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
