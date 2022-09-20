package top.xiaorang.springframework.aop;

/**
 * @author liulei
 * @description
 * @github <a href="https://github.com/xihuanxiaorang/tiny">tiny</a>
 * @Copyright 博客：<a href="https://xiaorang.top">小让的糖果屋</a>  - show me the code
 * @date 2022/9/21 2:54
 */
public interface Pointcut {
    /**
     * Return the ClassFilter for this pointcut.
     *
     * @return the ClassFilter (never {@code null})
     */
    ClassFilter getClassFilter();

    /**
     * Return the MethodMatcher for this pointcut.
     *
     * @return the MethodMatcher (never {@code null})
     */
    MethodMatcher getMethodMatcher();
}
