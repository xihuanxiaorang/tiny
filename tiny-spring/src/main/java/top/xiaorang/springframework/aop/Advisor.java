package top.xiaorang.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:14
 */
public interface Advisor {
    /**
     * Common placeholder for an empty {@code Advice} to be returned from
     * {@link #getAdvice()} if no proper advice has been configured (yet).
     *
     * @since 5.0
     */
    Advice EMPTY_ADVICE = new Advice() {
    };

    /**
     * Return the advice part of this aspect. An advice may be an
     * interceptor, a before advice, a throws advice, etc.
     *
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
