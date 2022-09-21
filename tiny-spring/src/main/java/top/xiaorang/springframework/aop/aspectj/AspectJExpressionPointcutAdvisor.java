package top.xiaorang.springframework.aop.aspectj;

import top.xiaorang.springframework.aop.Pointcut;
import top.xiaorang.springframework.aop.support.AbstractGenericPointcutAdvisor;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 15:20
 */
public class AspectJExpressionPointcutAdvisor extends AbstractGenericPointcutAdvisor {
    // 切面
    private AspectJExpressionPointcut pointcut;
    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Pointcut getPointcut() {
        if (pointcut == null) {
            pointcut = new AspectJExpressionPointcut(expression);
        }
        return this.pointcut;
    }
}
