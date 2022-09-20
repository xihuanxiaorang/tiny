package top.xiaorang.springframework.aop.support;

import top.xiaorang.springframework.aop.Pointcut;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 3:11
 */
public interface ExpressionPointcut extends Pointcut {
    /**
     * 返回此切入点的字符串表达式
     *
     * @return 切入点的字符串表达式
     */
    String getExpression();
}
