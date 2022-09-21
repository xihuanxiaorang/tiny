package top.xiaorang.springframework.aop.support;

import org.aopalliance.aop.Advice;
import top.xiaorang.springframework.aop.PointcutAdvisor;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:24
 */
public abstract class AbstractGenericPointcutAdvisor implements PointcutAdvisor {
    // 具体的拦截方法
    private Advice advice = EMPTY_ADVICE;

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    /**
     * Specify the advice that this advisor should apply.
     */
    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public String toString() {
        return getClass().getName() + ": advice [" + getAdvice() + "]";
    }
}
